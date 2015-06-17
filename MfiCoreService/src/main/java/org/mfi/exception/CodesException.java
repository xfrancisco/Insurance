package org.mfi.exception;

public class CodesException extends MfcException {

	private static final long serialVersionUID = 4009089957767207710L;

	public static enum ErrorCode {
		ERR_BIZ_CODES_UNKNOWN_TABLE,
		ERR_BIZ_CODES_INVALID_OBJECT
	}

	public CodesException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public CodesException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
