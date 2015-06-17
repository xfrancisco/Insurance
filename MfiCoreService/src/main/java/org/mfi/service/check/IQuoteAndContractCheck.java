package org.mfi.service.check;

import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_quote;
import org.mfi.dto.contract.ContractDto;
import org.mfi.exception.QuoteAndContractException;

public interface IQuoteAndContractCheck {

	Cod_duration checkDuration(String cduration) throws QuoteAndContractException;

	Cod_quotestatus checkQuoteStatus(String cquotestatus) throws QuoteAndContractException;

	Cli_quote checkQuote(long numcli, int numquote) throws QuoteAndContractException;

	Cli_contract checkContract(long numcli, int numcon) throws QuoteAndContractException;

	ContractDto checkContractDto(long numcli, int numcon) throws QuoteAndContractException;

	Cod_frequency checkFrequency(String cfrequency) throws QuoteAndContractException;

}
