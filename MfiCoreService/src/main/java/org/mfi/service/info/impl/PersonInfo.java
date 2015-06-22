package org.mfi.service.info.impl;

import static org.hibernate.criterion.Projections.property;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Subqueries.propertyIn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mfi.conf.Cod_catcli;
import org.mfi.conf.Cod_civility;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IPersonInfo;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class PersonInfo extends ServiceCore implements IPersonInfo {

	@Override
	public Cli_client getPerson(final long numcli) {
		return genericDao.get(Cli_client.class, numcli);
	}

	@Override
	public Cod_civility getCivility(final String ccivil) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_civility.class);
		criteria.add(Restrictions.eq("ccivil", ccivil));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_catcli getCategory(final String ccatcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_catcli.class);
		criteria.add(Restrictions.eq("ccatcli", ccatcli));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_catcli> getCategories(final long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_catcli.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cli_client getBroker(final long numcli) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indbroker", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getFirstByCriteria(mainQuery);
	}

	@Override
	public Cli_client getInsurer(final long numcli) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indinsurance", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery));
		subQuery2.add(eq("numcli", numcli)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getFirstByCriteria(mainQuery);
	}

	@Override
	public Cli_client getClient(final long numcli) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indclient", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery));
		subQuery2.add(eq("numcli", numcli)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getFirstByCriteria(mainQuery);
	}

	@Override
	public Cli_client getAgency(final long numcli) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indagency", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery));
		subQuery2.add(eq("numcli", numcli)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getFirstByCriteria(mainQuery);
	}

	@Override
	public boolean isAgency(final long numcli) {
		Cli_client cliClient = getAgency(numcli);
		if (cliClient != null)
			return true;
		return false;
	}

	@Override
	public String getPersonLabel(long numcli) {
		Cli_client person = getPerson(numcli);
		Cod_civility codCivility = genericDao.get(Cod_civility.class, person.getCcivil());
		StringBuffer result = new StringBuffer("");
		result.append(codCivility.getLcivil());
		result.append(" ");
		if (Strings.isNullOrEmpty(person.getCompanyname())) {
			result.append(person.getName());
			result.append(" ");
			result.append(person.getFirstname());
		} else
			result.append(person.getCompanyname());
		return result.toString();
	}

}
