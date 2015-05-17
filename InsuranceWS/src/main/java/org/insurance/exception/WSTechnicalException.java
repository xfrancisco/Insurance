package org.insurance.exception;


public class WSTechnicalException extends InsuranceException {

	private static final long serialVersionUID = -3394484987737334419L;

	public static final String ERR_TECH_GEN_DEFAULT = "ERR_TECH_GEN_DEFAULT";
	public static final String ERR_TECH_CODETABLE = "ERR_TECH_CODETABLE";
	
	public WSTechnicalException(String errorCode) {
		super(errorCode);
	}

	public WSTechnicalException(String errorCode, Object... messageArgs) {
		super(errorCode, messageArgs);
	}

	public WSTechnicalException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public WSTechnicalException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public WSTechnicalException(String errorCode, String errorMessage, Object... messageArgs) {
		super(errorCode, errorMessage, messageArgs);
	}

	public WSTechnicalException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	public WSTechnicalException(Throwable cause, String errorCode, Object... messageArgs) {
		super(cause, errorCode, messageArgs);
	}
}

