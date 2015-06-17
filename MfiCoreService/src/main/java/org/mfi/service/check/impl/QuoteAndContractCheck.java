package org.mfi.service.check.impl;

import javax.inject.Inject;

import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_quote;
import org.mfi.dto.contract.ContractDto;
import org.mfi.exception.QuoteAndContractException;
import org.mfi.exception.QuoteAndContractException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.util.MappingUtils;

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
	public Cod_frequency checkFrequency(final String cfrequency) throws QuoteAndContractException {
		Cod_frequency codFrequency = quoteAndContractInfo.getFrequency(cfrequency);
		if (codFrequency == null) {
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_FREQUENCY, cfrequency);
		}
		return codFrequency;
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
	public Cli_contract checkContract(final long numcli, final int numcon) throws QuoteAndContractException {
		Cli_contract contract = quoteAndContractInfo.getContract(numcli, numcon);
		if (contract == null)
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_CONTRACT, numcli, numcon);
		return contract;
	}

	@Override
	public ContractDto checkContractDto(final long numcli, final int numcon) throws QuoteAndContractException {
		ContractDto contract = quoteAndContractInfo.getContractDto(numcli, numcon);
		if (contract == null)
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_CONTRACT, numcli, numcon);
		return contract;
	}

}
