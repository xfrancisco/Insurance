package org.insurance.service.transactional;

import org.insurance.data.Cli_quote;
import org.insurance.exception.QuoteAndContractException;

public interface IQuoteOperation {

	void insertQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote);

	void updateQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote, Cli_quote oldQuote);

	void validateQuote(String cuser, long numcli, int numquote, int numcon) throws QuoteAndContractException;
}
