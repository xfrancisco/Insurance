package org.mfi.exception;

public class CommonException extends MfcException {

	private static final long serialVersionUID = -5859084017461071102L;

	public static enum ErrorCode {
		ERR_BIZ_COMMON_INVALID_AMOUNT,
		ERR_BIZ_COMMON_INVALID_PERCENTAGE,
		ERR_BIZ_COMMON_INVALID_PERIOD,
		ERR_BIZ_COMMON_INVALID_NUMBER,
		ERR_BIZ_COMMON_INVALID_GLOBAL_SHARE
	}

	public CommonException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public CommonException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
