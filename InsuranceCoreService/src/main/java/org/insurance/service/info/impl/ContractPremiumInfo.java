package org.insurance.service.info.impl;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ne;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.data.Cli_guarantee;
import org.insurance.data.Cpt_guarcommi;
import org.insurance.data.Cpt_guardispatch;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IContractPremiumInfo;
import org.springframework.stereotype.Service;

@Service
public class ContractPremiumInfo extends ServiceCore implements IContractPremiumInfo {

	@Override
	public List<Cli_guarantee> getGuarantees(long numcli, int numcon) {
		Date today = dbHelper.getToday();
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_guarantee.class);
		criteria.add(eq("numcli", numcli));
		criteria.add(eq("numcon", numcon));
		criteria.add(Restrictions.le("startval", today));
		criteria.add(Restrictions.or(Restrictions.isNull("endval"), Restrictions.ge("endval", today)));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cpt_guarcommi getBrokerCommission(long numguarantee, long numclibroker) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarcommi.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numclicommi", numclibroker));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cpt_guardispatch getLeaderShare(long numguarantee, long numclileader) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guardispatch.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numcliins", numclileader));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cpt_guardispatch> getDispatches(long numguarantee, long numclileader) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guardispatch.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(ne("numcliins", numclileader));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cpt_guarcommi getInsurerCommission(long numguarantee, Long numcliinsurer) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarcommi.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numclicommi", numcliinsurer));
		return genericDao.getFirstByCriteria(criteria);
	}
}
