package org.insurance.service.manager;

import org.insurance.data.Cli_quote;

public interface IQuoteManager {

	int insertQuote(long numcli, String cuser, Cli_quote cliQuote);

}
