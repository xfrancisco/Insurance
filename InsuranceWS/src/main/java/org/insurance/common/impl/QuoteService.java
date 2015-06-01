package org.insurance.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IQuoteService;
import org.insurance.data.Cli_quote;
import org.insurance.exception.InsuranceException;
import org.insurance.in.NewQuoteIn;
import org.insurance.in.UpdateQuoteIn;
import org.insurance.out.QuoteOut;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IQuoteInfo;
import org.insurance.service.manager.IQuoteManager;
import org.insurance.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class QuoteService implements IQuoteService {

	@Inject
	private IQuoteManager quoteManager;

	@Inject
	private IQuoteInfo quoteInfo;

	@Inject
	private IUserCheck usercheck;

	static final Logger logger = Logger.getLogger(QuoteService.class);

	@Override
	public QuoteOut insertQuote(String userId, NewQuoteIn newQuoteIn) throws InsuranceException {
		usercheck.checkUser(userId);
		Cli_quote quote = populateQuote(newQuoteIn);
		int numquote = quoteManager.insertQuote(newQuoteIn.getPersonId(), userId, quote);
		return getQuote(userId, newQuoteIn.getPersonId(), numquote);
	}

	private Cli_quote populateQuote(NewQuoteIn quoteIn) {
		Cli_quote result = new Cli_quote();
		if (quoteIn != null) {
			result.setAcceptancedate(DateUtils.parseStringToSqlDate(quoteIn.getAcceptanceDate()));
			result.setCbranch(quoteIn.getBranchId());
			result.setCcategory(quoteIn.getCategoryId());
			result.setCduration(quoteIn.getDurationId());
			result.setCquotestatus(quoteIn.getQuoteStatusId());
			result.setCuseruw(quoteIn.getUnderwriterId());
			result.setEndval(DateUtils.parseStringToSqlDate(quoteIn.getValidityEndDate()));
			result.setGuaranteedamount(quoteIn.getGuaranteedAmount());
			result.setNumclibroker(quoteIn.getBrokerId());
			result.setNumclileader(quoteIn.getLeaderId());
			result.setPremiumamount(quoteIn.getPremiumAmount());
			result.setReceptiondate(DateUtils.parseStringToSqlDate(quoteIn.getReceptionDate()));
			result.setSharepart(quoteIn.getShare());
			result.setStartval(DateUtils.parseStringToSqlDate(quoteIn.getWorkingDate()));
		}
		return result;
	}

	@Override
	public QuoteOut getQuote(String userId, Long personId, Integer quoteId) throws InsuranceException {
		usercheck.checkUser(userId);
		Cli_quote quote = quoteInfo.getQuote(personId, quoteId);
		return populateQuote(personId, quote);
	}

	@Override
	public List<QuoteOut> getQuotes(String userId, Long personId) throws InsuranceException {
		usercheck.checkUser(userId);
		List<Cli_quote> quotes = quoteInfo.getQuotes(personId);
		return populateQuotes(personId, quotes);
	}

	private QuoteOut populateQuote(Long personId, Cli_quote quote) {
		QuoteOut result = new QuoteOut();
		if (quote != null) {
			result.setPersonId(personId);
			result.setQuoteId(quote.getNumquote());
			result.setAcceptanceDate(DateUtils.formatDate(quote.getAcceptancedate()));
			result.setBranchId(quote.getCbranch());
			result.setBrokerId(quote.getNumclibroker());
			result.setCategoryId(quote.getCcategory());
			result.setDurationId(quote.getCduration());
			result.setGuaranteedAmount(quote.getGuaranteedamount());
			result.setLeaderId(quote.getNumclileader());
			result.setPremiumAmount(quote.getPremiumamount());
			result.setQuoteStatusId(quote.getCquotestatus());
			result.setReceptionDate(DateUtils.formatDate(quote.getReceptiondate()));
			result.setShare(quote.getSharepart());
			result.setUnderwriterId(quote.getCuseruw());
			result.setValidityEndDate(DateUtils.formatDate(quote.getEndval()));
			result.setWorkingDate(DateUtils.formatDate(quote.getStartval()));
		}
		return result;
	}

	private List<QuoteOut> populateQuotes(Long personId, List<Cli_quote> quote) {
		List<QuoteOut> result = new ArrayList<QuoteOut>();
		for (Cli_quote cliQuote : quote) {
			result.add(populateQuote(personId, cliQuote));
		}
		return result;
	}

	@Override
	public QuoteOut updateQuote(String userId, UpdateQuoteIn updateQuoteIn) throws InsuranceException {
		// TODO Auto-generated method stub
		usercheck.checkUser(userId);
		return null;
	}

}
