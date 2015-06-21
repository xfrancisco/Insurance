package org.mfi.service.info.impl;

import static org.hibernate.criterion.Projections.property;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ne;
import static org.hibernate.criterion.Subqueries.propertyIn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mfi.conf.Cod_fee;
import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_fee;
import org.mfi.data.Cpt_guarbroker;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.data.Cpt_guardispatch;
import org.mfi.data.Cpt_guarplacement;
import org.mfi.data.Cpt_leadingfee;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IContractPremiumInfo;
import org.springframework.stereotype.Service;

@Service
public class ContractPremiumInfo extends ServiceCore implements IContractPremiumInfo {

	@Override
	public List<Cli_guarantee> getGuarantees(final long numcli, final int numcon) {
		Date today = dbHelper.getToday();
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_guarantee.class);
		criteria.add(eq("numcli", numcli));
		criteria.add(eq("numcon", numcon));
		criteria.add(Restrictions.le("startval", today));
		criteria.add(Restrictions.or(Restrictions.isNull("endval"), Restrictions.ge("endval", today)));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cpt_guarbroker getBrokerCommission(final long numguarantee, final long numclibroker) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarbroker.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numclibro", numclibroker));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cpt_guardispatch getLeaderShare(final long numguarantee, final long numclileader) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guardispatch.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numcliins", numclileader));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cpt_guardispatch> getDispatches(final long numguarantee, final long numclileader) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guardispatch.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(ne("numcliins", numclileader));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cpt_guardispatch> getDispatches(final long numguarantee) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guardispatch.class);
		criteria.add(eq("numguarantee", numguarantee));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cpt_guarcommi getAgencyCommission(final long numguarantee, final long numcliinsurer) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarcommi.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numclicommi", numcliinsurer));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cpt_fee getInitialFees(final long numcli, final int numcon) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_fee.class);
		subQuery.add(eq("indpolicyfee", "1")).setProjection(property("cfee"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cpt_fee.class);
		mainQuery.add(propertyIn("cfee", subQuery));
		mainQuery.add(eq("numcli", numcli));
		mainQuery.add(eq("numcon", numcon));

		return genericDao.getFirstByCriteria(mainQuery);
	}

	@Override
	public List<Cpt_leadingfee> getLeadingCommission(final long numguarantee) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_leadingfee.class);
		criteria.add(eq("numguarantee", numguarantee));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cpt_guarcommi> getAgencyCommission(final long numguarantee) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarcommi.class);
		criteria.add(eq("numguarantee", numguarantee));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cpt_guarplacement> getAgencyPlacement(final long numguarantee) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarplacement.class);
		criteria.add(eq("numguarantee", numguarantee));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cpt_guarplacement getAgencyPlacement(final long numguarantee, final long numcliinsurer) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_guarplacement.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numcliins", numcliinsurer));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cpt_leadingfee getLeadingCommission(final long numguarantee, final long numclisrc, final long numclidest) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_leadingfee.class);
		criteria.add(eq("numguarantee", numguarantee));
		criteria.add(eq("numclisrc", numclisrc));
		criteria.add(eq("numclidest", numclidest));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cpt_fee> getFees(long numcli, int numcon) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cpt_fee.class);
		criteria.add(eq("numcli", numcli));
		criteria.add(eq("numcon", numcon));
		return genericDao.getByCriteria(criteria);
	}
}
