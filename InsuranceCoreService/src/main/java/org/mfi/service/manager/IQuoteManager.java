package org.mfi.service.manager;

import org.mfi.data.Cli_quote;
import org.mfi.exception.MfcException;

public interface IQuoteManager {

	int insertQuote(long numcli, String cuser, Cli_quote cliQuote) throws MfcException;

	void updateQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote) throws MfcException;

}
