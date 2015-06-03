package org.insurance.exception;

public class PremiumException extends InsuranceException {

	private static final long serialVersionUID = -5693605624947958688L;

	public static enum ErrorCode {
		ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH,
		ERR_BIZ_PREMIUM_UNKNWOWN_CATEGORY,
		ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH_CATEGORY,
		ERR_BIZ_PREMIUM_INVALID_BRANCH,
		ERR_BIZ_PREMIUM_INVALID_CATEGORY;
	}

	public PremiumException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public PremiumException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}