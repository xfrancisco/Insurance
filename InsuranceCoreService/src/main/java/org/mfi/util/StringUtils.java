package org.mfi.util;

import com.google.common.base.Strings;

public class StringUtils {

	public static String escapeDoubleQuote(String string) {
		if (Strings.isNullOrEmpty(string)) {
			return "";
		}
		return string.replaceAll("(\\\\)", "\\\\\\\\").replaceAll("(\")", "\\\\\"");
	}

}
