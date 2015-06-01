package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.exception.QuoteAndContractException;
import org.insurance.exception.QuoteAndContractException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.util.MappingUtils;

public class QuoteAndContractCheck extends ServiceCore implements IQuoteAndContractCheck {

	@Inject
	private IQuoteAndContractInfo quoteInfo;

	@Override
	public Cod_duration checkDuration(final String cduration) throws QuoteAndContractException {
		Cod_duration codDuration = quoteInfo.getDuration(cduration);
		if (codDuration == null) {
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_DURATION, cduration);
		}
		return codDuration;
	}

	@Override
	public Cod_quotestatus checkQuoteStatus(final String cquotestatus) throws QuoteAndContractException {
		Cod_quotestatus codQuotestatus = quoteInfo.getQuoteStatus(cquotestatus);
		if (codQuotestatus == null) {
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_QUOTE_STATUS, cquotestatus);
		}
		if (MappingUtils.toBoolean(codQuotestatus.getIndvalidated()))
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_FORBIDDEN_QUOTE_STATUS, cquotestatus);

		return codQuotestatus;
	}

}
