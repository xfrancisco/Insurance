package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.dto.contract.ContractDto;

public interface IQuoteAndContractInfo {

	List<Cod_quotestatus> getQuoteStatus();

	int getNextNumQuote(long numcli);

	Cli_quote getQuote(long numcli, int numquote);

	List<Cli_quote> getQuotes(long numcli);

	Cod_duration getDuration(String cduration);

	Cod_quotestatus getQuoteStatus(String cquotestatus);

	ContractDto getContract(long numcli, int numcon);

	List<ContractDto> getContracts(long personId);

	int getNextNumContract(long numcli);

	Cod_quotestatus getValidatedQuoteStatus();

}
