package org.mfi.service.manager.impl;

import javax.inject.Inject;

import org.mfi.conf.Cod_branch;
import org.mfi.conf.Cod_category;
import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_quote;
import org.mfi.exception.MfcException;
import org.mfi.exception.PremiumException;
import org.mfi.exception.QuoteAndContractException;
import org.mfi.exception.PremiumException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.ICommonCheck;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IPremiumCheck;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.manager.IQuoteManager;
import org.mfi.service.transactional.IQuoteOperation;
import org.mfi.util.MappingUtils;
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
	public int insertQuote(long numcli, String cuser, Cli_quote cliQuote) throws MfcException {
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
	public void updateQuote(long numcli, int numquote, String cuser, Cli_quote cliQuote) throws MfcException {
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