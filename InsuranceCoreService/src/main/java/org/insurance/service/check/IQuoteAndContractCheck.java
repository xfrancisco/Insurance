package org.insurance.service.check;

import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.QuoteAndContractException;

public interface IQuoteAndContractCheck {

	Cod_duration checkDuration(String cduration) throws QuoteAndContractException;

	Cod_quotestatus checkQuoteStatus(String cquotestatus) throws QuoteAndContractException;

	Cli_quote checkQuote(long numcli, int numquote) throws QuoteAndContractException;

	ContractDto checkContract(long numcli, int numcon) throws QuoteAndContractException;

}
