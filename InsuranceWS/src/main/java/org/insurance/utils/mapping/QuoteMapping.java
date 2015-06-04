package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.exception.CommonException;
import org.insurance.in.QuoteIn;
import org.insurance.out.QuoteOut;
import org.insurance.out.QuoteStatusOut;
import org.insurance.util.DateUtils;
import org.insurance.util.MappingUtils;

public final class QuoteMapping {

	public static QuoteStatusOut populateQuoteStatusOut(Cod_quotestatus codQuotestatus) {
		QuoteStatusOut result = new QuoteStatusOut();
		result.setId(codQuotestatus.getCquotestatus());
		result.setLabel(codQuotestatus.getLquotestatus());
		result.setIsValid(MappingUtils.toBoolean(codQuotestatus.getIndvali()));
		result.setValidated(MappingUtils.toBoolean(codQuotestatus.getIndvalidated()));
		result.setPending(MappingUtils.toBoolean(codQuotestatus.getIndpending()));
		result.setAccepted(MappingUtils.toBoolean(codQuotestatus.getIndaccepted()));
		result.setRefused(MappingUtils.toBoolean(codQuotestatus.getIndrefused()));
		result.setAborted(MappingUtils.toBoolean(codQuotestatus.getIndaborted()));
		return result;
	}

	public static List<QuoteStatusOut> populateQuoteStatusOut(List<Cod_quotestatus> status) {
		List<QuoteStatusOut> result = new ArrayList<QuoteStatusOut>();
		for (Cod_quotestatus codQuotestatus : status) {
			QuoteStatusOut tmp = populateQuoteStatusOut(codQuotestatus);
			result.add(tmp);
		}
		return result;
	}

	public static Cli_quote populateQuote(QuoteIn quoteIn) throws CommonException {
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

	public static QuoteOut populateQuote(Long personId, Cli_quote quote, boolean withComment) {
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

	public static List<QuoteOut> populateQuotes(Long personId, List<Cli_quote> quote) {
		List<QuoteOut> result = new ArrayList<QuoteOut>();
		for (Cli_quote cliQuote : quote) {
			result.add(populateQuote(personId, cliQuote, false));
		}
		return result;
	}
}
