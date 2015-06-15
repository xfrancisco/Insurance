package org.mfi.exception;

import java.util.Arrays;

import org.mfi.util.NonNullUtils;

public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = -3394484987737334419L;

	public static final String ERR_TECH_GEN_DEFAULT = "ERR_TECH_GEN_DEFAULT";
	public static final String ERR_TECH_CODETABLE = "ERR_TECH_CODETABLE";

	public static enum ErrorCode {
		//@formatter:off
		ERR_TECH_MISSING_DATA,
		ERR_TECH_INVALID_PLSQLJSON,
		ERR_TECH_JSON_PARSING,
		ERR_TECH_SQLEXCEPTION,
		ERR_TECH_MISSING_SETTINGS,
		ERR_TECH_INVALID_LINKBITMAP,
		ERR_TECH_INVALID_LINKMASK,
		ERR_TECH_SQLCONNECTION,
		ERR_TECH_INVALID_ORACLETYPE,
		ERR_TECH_INVALID_DATE,
		ERR_TECH_MISSINGCGAWEB_KEY,
		ERR_TECH_INVALIDCGAWEB_KEY,
		ERR_TECH_INVALIDTIMEPERIOD,
		ERR_TECH_SQLBINDEXCEPTION,
		ERR_TECH_HIBERNATE_READ,
		ERR_TECH_HIBERNATE_WRITE,
		ERR_TECH_HIBERNATE_NAMEDQUERY,
		ERR_TECH_STATEMENT_NOT_EXECUTED,
		ERR_TECH_NOSESSION,
		ERR_TECH_UNMAPPED_SQLTYPE,
		ERR_TECH_SOAP_CLIENT,
		ERR_TECH_NON_NULL_EXPECTED,
		ERR_TECH_INSTROSPECTION_FAILURE,		
		ERR_TECH_GEN_DEFAULT,
		ERR_TECH_CLONE,
		ERR_TECH_VALIDATION,
		ERR_TECH_OCCULTATION_DATA,
		ERR_TECH_JAXB_PARSING, ERR_TECH_CODETABLE;
		//@formatter:on
	}

	private final ErrorCode errorCode;

	private final Object[] messageArgs;

	public TechnicalException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = NonNullUtils.EMPTY_ARRAY;
	}

	public TechnicalException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public TechnicalException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = NonNullUtils.EMPTY_ARRAY;
	}

	public TechnicalException(ErrorCode errorCode, Throwable cause, Object... messageArgs) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public String getErrorCode() {
		return errorCode.name();
	}

	public Object[] getMessageArgs() {
		Object[] localMessageArgs = Arrays.copyOf(this.messageArgs, this.messageArgs.length);
		return localMessageArgs;
	}
}
