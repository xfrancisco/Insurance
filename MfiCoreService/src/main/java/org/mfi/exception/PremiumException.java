package org.mfi.exception;

public class PremiumException extends MfcException {

	private static final long serialVersionUID = -5693605624947958688L;

	public static enum ErrorCode {
		ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH,
		ERR_BIZ_PREMIUM_UNKNWOWN_CATEGORY,
		ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH_CATEGORY,
		ERR_BIZ_PREMIUM_INVALID_BRANCH,
		ERR_BIZ_PREMIUM_INVALID_CATEGORY,
		ERR_BIZ_PREMIUM_UNKNWOWN_SECTION,
		ERR_BIZ_PREMIUM_UNKNWOWN_GUARANTEE,
		ERR_BIZ_PREMIUM_UNKNWOWN_PREMIUM,
		ERR_BIZ_PREMIUM_UNKNWOWN_TAX,
		ERR_BIZ_PREMIUM_UNKNWOWN_PREMIUM_CONF,
		ERR_BIZ_PREMIUM_INSURER_ALREADY_IN_SHARE;
	}

	public PremiumException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public PremiumException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
