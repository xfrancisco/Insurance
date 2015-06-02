package org.insurance.exception;

public class CommonException extends InsuranceException {

	private static final long serialVersionUID = -5859084017461071102L;

	public static enum ErrorCode {
		ERR_BIZ_COMMON_INVALID_AMOUNT,
		ERR_BIZ_COMMON_INVALID_PERCENTAGE,
		ERR_BIZ_COMMON_INVALID_PERIOD,
		ERR_BIZ_COMMON_INVALID_NUMBER
	}

	public CommonException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public CommonException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
