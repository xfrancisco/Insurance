package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_duration;
import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.springframework.stereotype.Service;

@Service
public class QuoteAndContractInfo extends ServiceCore implements IQuoteAndContractInfo {

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

	@Override
	public Cod_duration getDuration(String cduration) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_duration.class);
		criteria.add(Restrictions.eq("cduration", cduration));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_quotestatus getQuoteStatus(String cquotestatus) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_quotestatus.class);
		criteria.add(Restrictions.eq("cquotestatus", cquotestatus));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

}
