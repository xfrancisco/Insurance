package org.insurance.exception;

public class MovementException extends InsuranceException {

	private static final long serialVersionUID = -6225864517719109954L;

	public static enum ErrorCode {
		ERR_BIZ_MOVEMENT_UNKNOWN_MOVEMENT,
		ERR_BIZ_MOVEMENT_SEARCH_CONTRAT_OR_QUOTE
	}

	public MovementException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public MovementException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
