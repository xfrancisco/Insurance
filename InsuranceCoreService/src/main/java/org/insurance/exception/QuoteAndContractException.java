package org.insurance.exception;

public class QuoteAndContractException extends InsuranceException {

	private static final long serialVersionUID = -7326234512455998707L;

	public static enum ErrorCode {
		ERR_BIZ_QUOTECONTRACT_UNKNOWN_DURATION,
		ERR_BIZ_QUOTECONTRACT_UNKNOWN_QUOTE_STATUS,
		ERR_BIZ_QUOTECONTRACT_FORBIDDEN_QUOTE_STATUS,
		ERR_BIZ_QUOTECONTRACT_INVALID_DURATION,
		ERR_BIZ_QUOTECONTRACT_INVALID_QUOTE_STATUS,
		ERR_BIZ_QUOTECONTRACT_UNKNOWN_QUOTE,
		ERR_BIZ_QUOTECONTRACT_UNKNOWN_CONTRACT
	}

	public QuoteAndContractException(ErrorCode errorCode) {
		super(errorCode.name());
	}

	public QuoteAndContractException(ErrorCode errorCode, Object... messageArgs) {
		super(errorCode.name(), messageArgs);
	}
}
