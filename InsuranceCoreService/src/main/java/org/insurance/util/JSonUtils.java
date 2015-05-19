package org.insurance.util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.insurance.exception.TechnicalException;
import org.insurance.exception.TechnicalException.ErrorCode;
import org.insurance.util.DateUtils.DatePattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSonUtils {

	private JSonUtils() {
	}

	private static void getAllFieldsNamesRec(Class<?> classToSerialize, HashSet<String> fieldsNameSet, final List<String> fieldToSerialize) {
		if (classToSerialize == null) {
			return;
		}
		Field[] fields = classToSerialize.getDeclaredFields();
		int len = fields.length;
		for (int i = 0; i < len; i++) {
			if (fieldToSerialize == null || fieldToSerialize.contains(fields[i].getName())) {
				fieldsNameSet.add(fields[i].getName());
			}
		}
		getAllFieldsNamesRec(classToSerialize.getSuperclass(), fieldsNameSet, fieldToSerialize);
	}

	private static String[] getAllFieldsNames(Class<?> classToSerialize, final List<String> fieldToSerialize) {
		HashSet<String> fieldsNameSet = new HashSet<String>();
		getAllFieldsNamesRec(classToSerialize, fieldsNameSet, fieldToSerialize);
		Object[] tmp = fieldsNameSet.toArray();
		String[] result = new String[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			result[i] = tmp[i].toString();
		}
		return result;
	}

	public static <T> T convertJsonStringToObject(String jsonString, Class<T> dtoClass) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonString, objectMapper.constructType(dtoClass));
		} catch (IOException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_JSON_PARSING, e);
		}
	}

	public static <T> T convertJsonStringToObject(String jsonString) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonString, new TypeReference<T>() {
			});
		} catch (IOException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_JSON_PARSING, e);
		}
	}

	/**
	 * Partie récursive de ObjectToJson, gère les type String,util.Date,sql.Date,Float,Double,Long,Integer,Collection et Object (dont les champs font partie des types cités précédement)
	 * @param obj Object à convertir en Json
	 * @param strbuff StringBuffer en IN/OUT
	 * @param includeNullField Si vrai on serialize également les champs nulls, sinon ils ne seront pas présents dans le flux json
	 */
	private static void objectToJsonRecursive(final Object obj, StringBuffer strbuff, boolean includeNullField, final List<String> fieldToSerialize) {
		if (obj == null) {
			strbuff.append("\"\"");
			return;
		}

		Class<?> objClass = obj.getClass();

		if (objClass.equals(String.class)) {
			strbuff.append("\"" + StringUtils.escapeDoubleQuote(obj.toString()) + "\"");
		} else if (objClass.equals(Boolean.class)) {
			strbuff.append("\"" + MappingUtils.boolToString((Boolean) obj) + "\"");
		} else if (objClass.equals(java.sql.Date.class)) {
			strbuff.append("\"" + DateUtils.formatDate((java.sql.Date) obj) + "\"");
		} else if (objClass.equals(java.util.Date.class)) {
			strbuff.append("\"" + DateUtils.formatDate((java.util.Date) obj, DatePattern.DATE_DD_MM_YYYY_HH_MM_SS) + "\"");
		} else if (objClass.equals(Long.class) || objClass.equals(Integer.class) || objClass.equals(Double.class) || objClass.equals(Float.class)) {
			strbuff.append("\"" + obj.toString().replace(',', '.') + "\"");
		} else if (obj instanceof Collection) {
			Collection<?> coll = (Collection<?>) obj;
			Iterator<?> it = coll.iterator();
			strbuff.append('[');
			boolean isFirst = true;
			while (it.hasNext()) {
				if (!isFirst) {
					strbuff.append(',');
				}
				objectToJsonRecursive(it.next(), strbuff, includeNullField, fieldToSerialize);
				isFirst = false;
			}
			strbuff.append(']');
		} else if (obj instanceof Object[]) {
			int len = Array.getLength(obj);
			strbuff.append('[');
			for (int i = 0; i < len; i++) {
				if (i > 0) {
					strbuff.append(',');
				}
				objectToJsonRecursive(Array.get(obj, i), strbuff, includeNullField, fieldToSerialize);
			}
			strbuff.append(']');
		} else {
			String[] fields = getAllFieldsNames(objClass, fieldToSerialize);//.getDeclaredFields();
			int len = fields.length;
			boolean isFirst = true;
			strbuff.append('{');
			for (int i = 0; i < len; i++) {
				String fieldname = fields[i];
				String fieldnameFirstUp = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
				//On va cherche le getter
				String methodeName = "get" + fieldnameFirstUp;
				Method methode = null;
				try {
					methode = objClass.getMethod(methodeName, null);
				} catch (NoSuchMethodException e) {
					methode = null;
				}
				//Si il existe le getter, on récupère la valeur                
				if (methode != null) {
					Object fieldValue = null;
					try {
						fieldValue = methode.invoke(obj, null);
					} catch (IllegalAccessException e) {
						fieldValue = null;
					} catch (InvocationTargetException e) {
						fieldValue = null;
					}

					if (fieldValue != null || includeNullField) {
						//On ajoute le champ
						if (!isFirst) {
							strbuff.append(',');
						}
						strbuff.append("\"" + fieldname + "\":");
						if (null != fieldValue) {
							objectToJsonRecursive(fieldValue, strbuff, includeNullField, fieldToSerialize);
						} else {
							strbuff.append("\"\"");
						}
						isFirst = false;
					}
				}
			}
			strbuff.append('}');
		}
	}

	/**
	 * ObjectToJson
	 * Sérialize un objet en json, l'object doit être de type:
	 * <ul>
	 * <li> String (les quotes et \ sont échappées)
	 * <li> java.util.Date
	 * <li> java.sql.Date
	 * <li> Float
	 * <li> Double
	 * <li> Long
	 * <li> Integer
	 * <li> Boolean
	 * <li> Collection (dont les élements font partie des types cités)
	 * <li> Array (dont les élements font partie des types cités)
	 * <li> Object (dont les champs font partie des types cités et un getter existe avec le nom: get[nom du champ avec 1ère lettre en majuscule], voir le paramètre includeNullField pour la gestion des champs null)
	 * </ul>
	 * @param obj à convertir en json
	 * @param includeNullField (uniquement pour les types Object hors Collection et Array) Si vrai on serialize les champs nulls des Objects, sinon ils ne seront pas présents dans le flux json
	 */
	public static String objectToJson(final Object obj, final boolean includeNullField, final List<String> fieldToSerialize) {
		StringBuffer strbuff = new StringBuffer();
		objectToJsonRecursive(obj, strbuff, includeNullField, fieldToSerialize);
		return strbuff.toString();
	}

	public static String objectToJson(final Object obj, final boolean includeNullField) {
		return objectToJson(obj, includeNullField, null);
	}

}
