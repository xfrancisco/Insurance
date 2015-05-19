package org.insurance.exception;

public class CodesException extends InsuranceException {

	private static final long serialVersionUID = 4009089957767207710L;

	public static enum ErrorCode {
		ERR_BIZ_CODES_UNKNOWN_TABLE
	}

	public CodesException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public CodesException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}