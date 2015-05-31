package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IQuoteInfo;
import org.springframework.stereotype.Service;

@Service
public class QuoteInfo extends ServiceCore implements IQuoteInfo {

	@Override
	public List<Cod_quotestatus> getQuoteStatus() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_quotestatus.class);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getByCriteria(criteria);
	}

}
