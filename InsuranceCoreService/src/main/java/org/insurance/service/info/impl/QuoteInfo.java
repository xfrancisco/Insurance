package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
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

	@Override
	public int getNextNumQuote(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.setProjection(Projections.max("numquote"));
		Integer result = genericDao.getFirstByCriteria(criteria);
		if (result == null)
			return 0;
		else
			return result + 1;
	}

	@Override
	public Cli_quote getQuote(long numcli, int numquote) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("numquote", numquote));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_quote> getQuotes(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		return genericDao.getByCriteria(criteria);
	}

}
