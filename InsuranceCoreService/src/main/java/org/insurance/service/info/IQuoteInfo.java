package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;

public interface IQuoteInfo {

	List<Cod_quotestatus> getQuoteStatus();

	int getNextNumQuote(long numcli);

	Cli_quote getQuote(long numcli, int numquote);

	List<Cli_quote> getQuotes(long numcli);
}
