package org.insurance.service.check;

import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.exception.QuoteAndContractException;

public interface IQuoteAndContractCheck {

	Cod_duration checkDuration(String cduration) throws QuoteAndContractException;

	Cod_quotestatus checkQuoteStatus(String cquotestatus) throws QuoteAndContractException;

}
