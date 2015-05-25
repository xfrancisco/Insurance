package org.insurance.exception;

public class UserException extends InsuranceException {

	private static final long serialVersionUID = 3752650279881879773L;

	public static enum ErrorCode {
		ERR_BIZ_USER_UNKNOWN_USER,
		ERR_BIZ_USER_UNKNOWN_ROLE;
	}

	public UserException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public UserException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
