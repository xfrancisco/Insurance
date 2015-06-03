package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.QuoteAndContractException;
import org.insurance.exception.QuoteAndContractException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.util.MappingUtils;

public class QuoteAndContractCheck extends ServiceCore implements IQuoteAndContractCheck {

	@Inject
	private IQuoteAndContractInfo quoteAndContractInfo;

	@Override
	public Cod_duration checkDuration(final String cduration) throws QuoteAndContractException {
		Cod_duration codDuration = quoteAndContractInfo.getDuration(cduration);
		if (codDuration == null) {
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_DURATION, cduration);
		}
		return codDuration;
	}

	@Override
	public Cod_quotestatus checkQuoteStatus(final String cquotestatus) throws QuoteAndContractException {
		Cod_quotestatus codQuotestatus = quoteAndContractInfo.getQuoteStatus(cquotestatus);
		if (codQuotestatus == null) {
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_QUOTE_STATUS, cquotestatus);
		}
		if (MappingUtils.toBoolean(codQuotestatus.getIndvalidated()))
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_FORBIDDEN_QUOTE_STATUS, cquotestatus);

		return codQuotestatus;
	}

	@Override
	public Cli_quote checkQuote(final long numcli, final int numquote) throws QuoteAndContractException {
		Cli_quote cliQuote = quoteAndContractInfo.getQuote(numcli, numquote);
		if (cliQuote == null)
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_QUOTE, numcli, numquote);
		return cliQuote;
	}

	@Override
	public ContractDto checkContract(final long numcli, final int numcon) throws QuoteAndContractException {
		ContractDto contract = quoteAndContractInfo.getContract(numcli, numcon);
		if (contract == null)
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_CONTRACT, numcli, numcon);
		return contract;
	}

}
