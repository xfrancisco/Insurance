package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_quote;
import org.insurance.exception.InsuranceException;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICommonCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IPremiumCheck;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.manager.IQuoteManager;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class QuoteManager extends ServiceCore implements IQuoteManager {

	@Inject
	private IQuoteOperation quoteOperation;

	@Inject
	private IQuoteAndContractInfo quoteInfo;

	@Inject
	private IQuoteAndContractCheck quoteContractCheck;

	@Inject
	private ICommonCheck commonCheck;

	@Inject
	private IPremiumCheck premiumCheck;

	@Inject
	private IUserCheck checkUser;

	@Inject
	private IPersonCheck personCheck;

	@Override
	public int insertQuote(long numcli, String cuser, Cli_quote cliQuote) throws InsuranceException {
		personCheck.checkAndGetPerson(cliQuote.getNumcli());
		personCheck.checkBroker(cliQuote.getNumclibroker());
		personCheck.checkLeader(cliQuote.getNumclileader());

		premiumCheck.checkBranch(cliQuote.getCbranch());
		premiumCheck.checkCategory(cliQuote.getCbranch(), cliQuote.getCcategory());
		quoteContractCheck.checkDuration(cliQuote.getCduration());
		quoteContractCheck.checkQuoteStatus(cliQuote.getCquotestatus());
		checkUser.checkUser(cliQuote.getCuseruw());

		commonCheck.checkAmount(cliQuote.getGuaranteedamount());
		commonCheck.checkAmount(cliQuote.getPremiumamount());
		commonCheck.checkPercentage(cliQuote.getSharepart());
		commonCheck.checkPeriod(cliQuote.getStartval(), cliQuote.getEndval());

		int numquote = quoteInfo.getNextNumQuote(numcli);
		quoteOperation.insertQuote(numcli, numquote, cuser, cliQuote);
		return numquote;
	}
}