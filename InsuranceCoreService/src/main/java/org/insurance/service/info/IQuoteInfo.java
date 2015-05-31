package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_quotestatus;

public interface IQuoteInfo {

	List<Cod_quotestatus> getQuoteStatus();
}
