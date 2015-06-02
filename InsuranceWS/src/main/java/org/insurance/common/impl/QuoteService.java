package org.insurance.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IQuoteService;
import org.insurance.data.Cli_quote;
import org.insurance.exception.CommonException;
import org.insurance.exception.InsuranceException;
import org.insurance.in.NewQuoteIn;
import org.insurance.in.UpdateQuoteIn;
import org.insurance.out.QuoteOut;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.manager.IQuoteManager;
import org.insurance.util.DateUtils;
import org.insurance.util.MappingUtils;
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

	static final Logger logger = Logger.getLogger(QuoteService.class);

	@Override
	public QuoteOut insertQuote(String userId, NewQuoteIn newQuoteIn) throws InsuranceException {
		usercheck.checkUser(userId);
		Cli_quote quote = populateQuote(newQuoteIn);
		int numquote = quoteManager.insertQuote(newQuoteIn.getPersonId(), userId, quote);
		return getQuote(userId, newQuoteIn.getPersonId(), numquote, false);
	}

	private Cli_quote populateQuote(NewQuoteIn quoteIn) throws CommonException {
		Cli_quote result = new Cli_quote();
		if (quoteIn != null) {
			result.setAcceptancedate(DateUtils.parseStringToSqlDate(quoteIn.getAcceptanceDate()));
			result.setCbranch(quoteIn.getBranchId());
			result.setCcategory(quoteIn.getCategoryId());
			result.setCduration(quoteIn.getDurationId());
			result.setCquotestatus(quoteIn.getQuoteStatusId());
			result.setCuseruw(quoteIn.getUnderwriterId());
			result.setEndval(DateUtils.parseStringToSqlDate(quoteIn.getValidityEndDate()));
			result.setGuaranteedamount(MappingUtils.toBigDecimal(quoteIn.getGuaranteedAmount()));
			result.setNumclibroker(quoteIn.getBrokerId());
			result.setNumclileader(quoteIn.getLeaderId());
			result.setPremiumamount(MappingUtils.toBigDecimal(quoteIn.getPremiumAmount()));
			result.setReceptiondate(DateUtils.parseStringToSqlDate(quoteIn.getReceptionDate()));
			result.setSharepart(MappingUtils.toBigDecimal(quoteIn.getShare()));
			result.setStartval(DateUtils.parseStringToSqlDate(quoteIn.getWorkingDate()));
			result.setCommentary(quoteIn.getComment());
		}
		return result;
	}

	@Override
	public QuoteOut getQuote(String userId, Long personId, Integer quoteId, boolean withComment) throws InsuranceException {
		usercheck.checkUser(userId);
		Cli_quote quote = quoteInfo.getQuote(personId, quoteId);
		return populateQuote(personId, quote, withComment);
	}

	@Override
	public List<QuoteOut> getQuotes(String userId, Long personId) throws InsuranceException {
		usercheck.checkUser(userId);
		List<Cli_quote> quotes = quoteInfo.getQuotes(personId);
		return populateQuotes(personId, quotes);
	}

	private QuoteOut populateQuote(Long personId, Cli_quote quote, boolean withComment) {
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
			result.setCancellationDate(DateUtils.formatDate(quote.getCancelDate()));
			result.setCancellationUser(quote.getCusercancel());
			if (withComment)
				result.setComment(quote.getCommentary());

		}
		return result;
	}

	private List<QuoteOut> populateQuotes(Long personId, List<Cli_quote> quote) {
		List<QuoteOut> result = new ArrayList<QuoteOut>();
		for (Cli_quote cliQuote : quote) {
			result.add(populateQuote(personId, cliQuote, false));
		}
		return result;
	}

	@Override
	public QuoteOut updateQuote(String userId, UpdateQuoteIn updateQuoteIn) throws InsuranceException {
		usercheck.checkUser(userId);
		Cli_quote cliQuote = populateQuote(updateQuoteIn);
		quoteManager.updateQuote(updateQuoteIn.getPersonId(), updateQuoteIn.getQuoteId(), userId, cliQuote);
		return getQuote(userId, updateQuoteIn.getPersonId(), updateQuoteIn.getQuoteId(), false);
	}

}
