package org.insurance.service.manager;

import org.insurance.data.Cli_quote;
import org.insurance.exception.InsuranceException;

public interface IQuoteManager {

	int insertQuote(long numcli, String cuser, Cli_quote cliQuote) throws InsuranceException;

	void updateQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote) throws InsuranceException;

}
