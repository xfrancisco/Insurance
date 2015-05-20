package org.insurance.exception;

public class AddressException extends InsuranceException {

	private static final long serialVersionUID = -904247426192516847L;

	public static enum ErrorCode {
		ERR_BIZ_ADDRESS_UNKNOWN_CITY_POSTAL_COUNTRY,
		ERR_BIZ_ADDRESS_MANDATORY_STREET2,
		ERR_BIZ_ADDRESS_MANDATORY_CITY_POSTAL_COUNTRY
	}

	public AddressException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public AddressException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
