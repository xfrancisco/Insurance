package org.insurance.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.insurance.exception.CommonException;
import org.insurance.exception.TechnicalException;
import org.insurance.exception.TechnicalException.ErrorCode;

import com.google.common.base.Strings;

public final class MappingUtils {

	private MappingUtils() {

	}

	public static boolean toBoolean(/*@Nullable*/String value) {
		//On test la nullabilité/chaine vide en 1er pour éviter d'avoir à tester la suite dans ce cas
		return StringUtils.isNotBlank(value)
				&& (StringUtils.equals("1", value) || StringUtils.equalsIgnoreCase("TRUE", value) || StringUtils.equalsIgnoreCase("ENABLE", value) || StringUtils
						.equalsIgnoreCase("ON", value));
	}

	public static String boolToString(boolean value) {
		return value ? "1" : "0";
	}

	/*@Nullable*/
	public static java.sql.Date toSqlDate(/*@Nullable*/Date utilDate) {
		if (utilDate != null) {
			return new java.sql.Date(utilDate.getTime());
		} else {
			return null;
		}
	}

	public static String encodeUrl(String urlToBeEncoded) {
		String encodedUrl = null;

		if (null != urlToBeEncoded) {
			try {
				encodedUrl = URLEncoder.encode(urlToBeEncoded, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				throw new TechnicalException(ErrorCode.ERR_TECH_GEN_DEFAULT, e);
			}
		}

		return encodedUrl;
	}

	public static BigDecimal toBigDecimal(final String value) throws CommonException {
		try {
			if (Strings.isNullOrEmpty(value))
				return null;
			BigDecimal result = new BigDecimal(value);
			return result;
		} catch (Exception e) {
			throw new CommonException(CommonException.ErrorCode.ERR_BIZ_COMMON_INVALID_NUMBER, value);
		}

	}

}
