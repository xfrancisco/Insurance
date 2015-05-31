package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.conf.Cod_quotestatus;
import org.insurance.out.QuoteStatusOut;
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
}
