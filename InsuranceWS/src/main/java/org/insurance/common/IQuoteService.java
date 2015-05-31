package org.insurance.common;

import java.util.List;

import org.insurance.in.NewQuoteIn;
import org.insurance.out.QuoteOut;

public interface IQuoteService {

	QuoteOut insertQuote(String userId, NewQuoteIn newQuoteIn);

	QuoteOut getQuote(String userId, Long personId, Integer quoteId);

	List<QuoteOut> getQuotes(String userId, Long personId);

}
