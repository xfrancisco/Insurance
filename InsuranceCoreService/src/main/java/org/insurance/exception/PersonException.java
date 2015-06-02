package org.insurance.exception;

public class PersonException extends InsuranceException {

	private static final long serialVersionUID = 3947747350958105938L;

	public static enum ErrorCode {
		ERR_BIZ_PERSON_UNKNOWN_CIVILITY,
		ERR_BIZ_PERSON_UNKNOWN_CATEGORY,
		ERR_BIZ_PERSON_MANDATORY_NAME_OR_COMPANYNAME,
		ERR_BIZ_PERSON_MANDATORY_COMPANYID,
		ERR_BIZ_PERSON_UNKNOWN_PERSON,
		ERR_BIZ_PERSON_UNKNOWN_BROKER,
		ERR_BIZ_PERSON_UNKNOWN_LEADER,
		ERR_BIZ_PERSON_UNKNOWN_CLIENT
	}

	public PersonException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public PersonException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
