package org.mfi.in;

import org.mfi.validation.constraints.Mandatory;

public class UpdateQuoteIn extends QuoteIn {

	@Mandatory
	private int quoteId;

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

}
