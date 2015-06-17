package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.mfi.common.IQuoteService;
import org.mfi.data.Cli_quote;
import org.mfi.exception.MfcException;
import org.mfi.in.QuoteIn;
import org.mfi.in.UpdateQuoteIn;
import org.mfi.out.QuoteOut;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.manager.IQuoteManager;
import org.mfi.utils.mapping.QuoteMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class QuoteService implements IQuoteService {

	@Inject
	private IQuoteManager quoteManager;

	@Inject
	private IQuoteAndContractInfo quoteInfo;

	@Inject
	private IUserCheck usercheck;

	@Inject
	private IPersonCheck personCheck;

	static final Logger logger = Logger.getLogger(QuoteService.class);

	@Override
	public QuoteOut insertQuote(String userId, QuoteIn newQuoteIn) throws MfcException {
		usercheck.checkUser(userId);
		Cli_quote quote = QuoteMapping.populateQuote(newQuoteIn);
		int numquote = quoteManager.insertQuote(newQuoteIn.getPersonId(), userId, quote);
		return getQuote(userId, newQuoteIn.getPersonId(), numquote, false);
	}

	@Override
	public QuoteOut getQuote(String userId, Long personId, Integer quoteId, boolean withComment) throws MfcException {
		usercheck.checkUser(userId);
		Cli_quote quote = quoteInfo.getQuote(personId, quoteId);
		return QuoteMapping.populateQuote(personId, quote, withComment);
	}

	@Override
	public List<QuoteOut> getQuotes(String userId, Long personId) throws MfcException {
		usercheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_quote> quotes = quoteInfo.getQuotes(personId);
		return QuoteMapping.populateQuotes(personId, quotes);
	}

	@Override
	public QuoteOut updateQuote(String userId, UpdateQuoteIn updateQuoteIn) throws MfcException {
		usercheck.checkUser(userId);
		Cli_quote cliQuote = QuoteMapping.populateQuote(updateQuoteIn);
		quoteManager.updateQuote(updateQuoteIn.getPersonId(), updateQuoteIn.getQuoteId(), userId, cliQuote);
		return getQuote(userId, updateQuoteIn.getPersonId(), updateQuoteIn.getQuoteId(), false);
	}

}
