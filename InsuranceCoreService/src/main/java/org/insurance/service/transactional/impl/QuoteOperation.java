package org.insurance.service.transactional.impl;

import org.insurance.data.Cli_quote;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class QuoteOperation extends ServiceCore implements IQuoteOperation {

	@Override
	public void insertQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote) {
		cliQuote.setNumcli(numcli);
		cliQuote.setCusercre(cuser);
		cliQuote.setCreationDate(dbHelper.getNow());
		cliQuote.setNumquote(numquote);
		genericDao.save(cliQuote);
	}

}
