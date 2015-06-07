package org.insurance.service.info.impl;

import static org.hibernate.criterion.Projections.property;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Subqueries.propertyIn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_catcli;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IPopulationInfo;
import org.springframework.stereotype.Service;

@Service
public class PopulationInfo extends ServiceCore implements IPopulationInfo {

	@Override
	public List<Cli_client> getClients() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indclient", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getBrokers() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indbroker", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getInsurers() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indinsurance", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getReinsurers() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indreinsurance", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getLawyers() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indlawyer", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getExperts() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indexpert", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getThirdParties() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indtiers", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getBeneficiaries() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indbenef", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getAgencies() {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indagency", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getClientsByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indclient", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getBrokersByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indbroker", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getInsurersByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indinsurance", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getReinsurersByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indreinsurance", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getLawyersByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indlawyer", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getExpertsByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indexpert", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getThirdPartiesByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indtiers", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getBeneficiariesByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indbenef", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getAgenciesByName(final String name) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_catcli.class);
		subQuery.add(eq("indagency", "1")).setProjection(property("ccatcli"));

		final DetachedCriteria subQuery2 = DetachedCriteria.forClass(Cli_catcli.class);
		subQuery2.add(propertyIn("ccatcli", subQuery)).setProjection(property("numcli"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cli_client.class);
		mainQuery.add(propertyIn("numcli", subQuery2));
		mainQuery.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(mainQuery);
	}

	@Override
	public List<Cli_client> getAll() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_client.class);
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cli_client> getAllByName(String name) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_client.class);
		criteria.add(Restrictions.or(Restrictions.ilike("name", name, MatchMode.ANYWHERE),
				Restrictions.ilike("companyname", name, MatchMode.ANYWHERE)));

		return genericDao.getByCriteria(criteria);
	}

}
