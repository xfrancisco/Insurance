package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.exception.InsuranceException;
import org.insurance.exception.PremiumException;
import org.insurance.exception.PremiumException.ErrorCode;
import org.insurance.exception.QuoteAndContractException;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICommonCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IPremiumCheck;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.manager.IQuoteManager;
import org.insurance.service.transactional.IQuoteOperation;
import org.insurance.util.MappingUtils;
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
		personCheck.checkClient(numcli);
		personCheck.checkBroker(cliQuote.getNumclibroker());
		personCheck.checkInsurer(cliQuote.getNumclileader());

		Cod_branch codBranch = premiumCheck.checkBranch(cliQuote.getCbranch());
		if (!MappingUtils.toBoolean(codBranch.getIndvali()))
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_INVALID_BRANCH, codBranch.getCbranch());

		Cod_category codCategory = premiumCheck.checkCategory(cliQuote.getCbranch(), cliQuote.getCcategory());
		if (!MappingUtils.toBoolean(codCategory.getIndvali()))
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_INVALID_CATEGORY, cliQuote.getCcategory());

		Cod_duration codDuration = quoteContractCheck.checkDuration(cliQuote.getCduration());
		if (codDuration != null && !MappingUtils.toBoolean(codDuration.getIndvali()))
			throw new QuoteAndContractException(QuoteAndContractException.ErrorCode.ERR_BIZ_QUOTECONTRACT_INVALID_DURATION, cliQuote.getCduration());

		Cod_quotestatus codQuotestatus = quoteContractCheck.checkQuoteStatus(cliQuote.getCquotestatus());
		if (!MappingUtils.toBoolean(codQuotestatus.getIndvali()))
			throw new QuoteAndContractException(QuoteAndContractException.ErrorCode.ERR_BIZ_QUOTECONTRACT_INVALID_QUOTE_STATUS,
					cliQuote.getCquotestatus());

		checkUser.checkUser(cliQuote.getCuseruw());

		if (cliQuote.getGuaranteedamount() != null)
			commonCheck.checkAmount(cliQuote.getGuaranteedamount());
		if (cliQuote.getPremiumamount() != null)
			commonCheck.checkAmount(cliQuote.getPremiumamount());
		if (cliQuote.getSharepart() != null)
			commonCheck.checkPercentage(cliQuote.getSharepart());

		commonCheck.checkPeriod(cliQuote.getStartval(), cliQuote.getEndval());

		int numquote = quoteInfo.getNextNumQuote(numcli);

		if (MappingUtils.toBoolean(codQuotestatus.getIndaborted())) {
			cliQuote.setCancelDate(dbHelper.getNow());
			cliQuote.setCusercancel(cuser);
		}
		quoteOperation.insertQuote(numcli, numquote, cuser, cliQuote);
		return numquote;
	}

	@Override
	public void updateQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote) throws InsuranceException {
		personCheck.checkClient(numcli);
		Cli_quote oldQuote = quoteContractCheck.checkQuote(numcli, numquote);
		personCheck.checkBroker(cliQuote.getNumclibroker());
		personCheck.checkInsurer(cliQuote.getNumclileader());

		premiumCheck.checkBranch(cliQuote.getCbranch());
		premiumCheck.checkCategory(cliQuote.getCbranch(), cliQuote.getCcategory());
		quoteContractCheck.checkDuration(cliQuote.getCduration());
		Cod_quotestatus codQuotestatus = quoteContractCheck.checkQuoteStatus(cliQuote.getCquotestatus());
		checkUser.checkUser(cliQuote.getCuseruw());

		if (cliQuote.getGuaranteedamount() != null)
			commonCheck.checkAmount(cliQuote.getGuaranteedamount());
		if (cliQuote.getPremiumamount() != null)
			commonCheck.checkAmount(cliQuote.getPremiumamount());
		if (cliQuote.getSharepart() != null)
			commonCheck.checkPercentage(cliQuote.getSharepart());
		commonCheck.checkPeriod(cliQuote.getStartval(), cliQuote.getEndval());

		if (MappingUtils.toBoolean(codQuotestatus.getIndaborted())) {
			cliQuote.setCancelDate(dbHelper.getNow());
			cliQuote.setCusercancel(cuser);
		}
		quoteOperation.updateQuote(numcli, numquote, cuser, cliQuote, oldQuote);
	}
}