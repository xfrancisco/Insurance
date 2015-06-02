package org.insurance.in;

import org.insurance.validation.constraints.Mandatory;

public class UpdateQuoteIn extends NewQuoteIn {

	@Mandatory
	private int quoteId;

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

}
