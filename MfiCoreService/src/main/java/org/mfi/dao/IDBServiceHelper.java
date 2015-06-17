package org.mfi.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

public interface IDBServiceHelper {

	PLSQLStatement preparePLSQL(String sqlStatement);

	/**
	 * Remonte une liste d'objet en fonction de la requête spécifiée. 
	 * 
	 * @param sqlQuery : la requête SQL
	 * @param dtoClass : la classe d'objet à getter
	 * @param sqlParams : une Map de paramètres
	 * @param scalarsMap : une Map de typage des propriétés remontées
	 * @return  : Une liste de <dtoClass>
	 */
	<T> List<T> sqlToDtoWithScalarsList(String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> scalarsMap);

	<T> T sqlToDto(String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> scalars);

	/*@Nullable*/
	<T> T sqlToDto(String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams);

	<T> List<T> sqlToDtoList(String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams);

	/*@Nullable*/
	<T> T sqlToDto(String sqlQuery, Class<T> dtoClass);

	<T> List<T> sqlToDtoList(String sqlQuery, Class<T> dtoClass);

	<T> T simpleSelect(String sqlQuery);

	<T> T simpleSelect(String sqlQuery, Map<String, Object> sqlParams);

	<T> T simpleSelect(String sqlQuery, Map<String, Object> sqlParams, Map<String, Type> paramTypes);

	/*@Nullable*/
	<T> T simpleSelectNullable(String sqlQuery);

	<T> List<T> simpleSelectList(String sqlQuery);

	<T> List<T> simpleSelectList(String sqlQuery, Map<String, Object> sqlParams);

	<T> List<T> sqlToDtoList(String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> paramTypes);

	<T> T simpleSelectNullable(String sqlQuery, Map<String, Object> sqlParams);
}
