package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.service.ServiceCore;
import org.insurance.service.check.IQuoteCheck;
import org.insurance.service.info.IQuoteInfo;

public class QuoteCheck extends ServiceCore implements IQuoteCheck {

	@Inject
	private IQuoteInfo quoteInfo;

}
