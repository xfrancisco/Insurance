package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.QuoteIn;
import org.mfi.in.UpdateQuoteIn;
import org.mfi.out.QuoteOut;

public interface IQuoteService {

	QuoteOut insertQuote(String userId, QuoteIn newQuoteIn) throws MfcException;

	QuoteOut getQuote(String userId, Long personId, Integer quoteId, boolean withComment) throws MfcException;

	List<QuoteOut> getQuotes(String userId, Long personId) throws MfcException;

	QuoteOut updateQuote(String userId, UpdateQuoteIn updateQuoteIn) throws MfcException;

}
