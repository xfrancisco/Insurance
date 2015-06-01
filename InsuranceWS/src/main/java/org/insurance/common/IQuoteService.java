package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.NewQuoteIn;
import org.insurance.in.UpdateQuoteIn;
import org.insurance.out.QuoteOut;

public interface IQuoteService {

	QuoteOut insertQuote(String userId, NewQuoteIn newQuoteIn) throws InsuranceException;

	QuoteOut getQuote(String userId, Long personId, Integer quoteId) throws InsuranceException;

	List<QuoteOut> getQuotes(String userId, Long personId) throws InsuranceException;

	QuoteOut updateQuote(String userId, UpdateQuoteIn updateQuoteIn) throws InsuranceException;

}
