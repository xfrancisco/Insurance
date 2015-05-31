package org.insurance.service.transactional;

import org.insurance.data.Cli_quote;

public interface IQuoteOperation {

	void insertQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote);

}
