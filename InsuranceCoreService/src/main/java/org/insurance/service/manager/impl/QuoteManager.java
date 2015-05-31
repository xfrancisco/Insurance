package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_quote;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IQuoteInfo;
import org.insurance.service.manager.IQuoteManager;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class QuoteManager extends ServiceCore implements IQuoteManager {

	@Inject
	private IQuoteOperation quoteOperation;

	@Inject
	private IQuoteInfo quoteInfo;

	@Override
	public int insertQuote(long numcli, String cuser, Cli_quote cliQuote) {
		int numquote = quoteInfo.getNextNumQuote(numcli);
		quoteOperation.insertQuote(numcli, numquote, cuser, cliQuote);
		return numquote;
	}

}