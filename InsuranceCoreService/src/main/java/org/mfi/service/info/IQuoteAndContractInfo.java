package org.mfi.service.info;

import java.util.List;

import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_quote;
import org.mfi.dto.contract.ContractDto;

public interface IQuoteAndContractInfo {

	List<Cod_quotestatus> getQuoteStatus();

	int getNextNumQuote(long numcli);

	Cli_quote getQuote(long numcli, int numquote);

	List<Cli_quote> getQuotes(long numcli);

	Cod_duration getDuration(String cduration);

	Cod_frequency getFrequency(String cfrequency);

	Cod_quotestatus getQuoteStatus(String cquotestatus);

	Cli_contract getContract(long numcli, int numcon);

	ContractDto getContractDto(long numcli, int numcon);

	List<Cli_contract> getContracts(long numcli);

	int getNextNumContract(long numcli);

	Cod_quotestatus getValidatedQuoteStatus();

}
