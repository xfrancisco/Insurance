package org.insurance.exception;

public class ContactException extends InsuranceException {

	private static final long serialVersionUID = -904247426192516847L;

	public static enum ErrorCode {
		ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_CITY_POSTAL_COUNTRY,
		ERR_BIZ_CONTACT_ADDRESS_MANDATORY_STREET2,
		ERR_BIZ_CONTACT_ADDRESS_MANDATORY_CITY_POSTAL_COUNTRY,
		ERR_BIZ_CONTACT_PHONE_UNKNOWN_DEFAULT_PHONE,
		ERR_BIZ_CONTACT_PHONE_UNKNOWN_DEFAULT_MOBILE,
		ERR_BIZ_CONTACT_EMAIL_UNKNOWN_DEFAULT_EMAIL,
		ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_DEFAULT_ADDRESS,
		ERR_BIZ_CONTACT_PHONE_UNKNOWN_TYPE,
		ERR_BIZ_CONTACT_EMAIL_UNKNOWN_TYPE,
		ERR_BIZ_CONTACT_EMAIL_INVALID_MAIL,
		ERR_BIZ_CONTACT_PHONE_INVALID_PHONENUMBER;
	}

	public ContactException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public ContactException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}