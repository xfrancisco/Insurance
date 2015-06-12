package org.insurance.service.info.impl;

import static org.hibernate.criterion.Projections.property;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Subqueries.propertyIn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_premiumconfig;
import org.insurance.conf.Cod_section;
import org.insurance.conf.Cod_tax;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IPremiumInfo;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class PremiumInfo extends ServiceCore implements IPremiumInfo {

	@Override
	public List<Cod_premiumconfig> getPremiums() {
		return genericDao.getList(Cod_premiumconfig.class);
	}

	@Override
	public Cod_branch getBranch(final String cbranch) {
		return genericDao.get(Cod_branch.class, cbranch);
	}

	@Override
	public Cod_category getCategory(final String ccategory) {
		return genericDao.get(Cod_category.class, ccategory);
	}

	@Override
	public Cod_section getSection(final String csection) {
		return genericDao.get(Cod_section.class, csection);
	}

	@Override
	public Cod_guarantee getGuarantee(final String cguarantee) {
		return genericDao.get(Cod_guarantee.class, cguarantee);
	}

	@Override
	public Cod_premium getPremium(final String cpremium) {
		return genericDao.get(Cod_premium.class, cpremium);
	}

	@Override
	public List<Cod_branch> getBranches() {
		return genericDao.getList(Cod_branch.class);
	}

	@Override
	public List<Cod_category> getCategories(final String cbranch) {
		if (Strings.isNullOrEmpty(cbranch))
			return genericDao.getList(Cod_category.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("cbranch", cbranch)).setProjection(property("ccategory"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_category.class);
			mainQuery.add(propertyIn("ccategory", subQuery));

			return genericDao.getByCriteria(mainQuery);
		}

	}

	@Override
	public List<Cod_section> getSections(final String cbranch, final String ccategory) {
		if (Strings.isNullOrEmpty(ccategory))
			return genericDao.getList(Cod_section.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("cbranch", cbranch));
			subQuery.add(eq("ccategory", ccategory)).setProjection(property("csection"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_section.class);
			mainQuery.add(propertyIn("csection", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

	@Override
	public List<Cod_guarantee> getGuarantees(final String cbranch, final String ccategory, final String csection) {
		if (Strings.isNullOrEmpty(csection))
			return genericDao.getList(Cod_guarantee.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("cbranch", cbranch));
			subQuery.add(eq("ccategory", ccategory));
			subQuery.add(eq("csection", csection)).setProjection(property("cguarantee"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_guarantee.class);
			mainQuery.add(propertyIn("cguarantee", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

	@Override
	public List<Cod_premium> getPremiums(final String cbranch, final String ccategory, final String csection, final String cguarantee) {
		if (Strings.isNullOrEmpty(cguarantee))
			return genericDao.getList(Cod_premium.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("cbranch", cbranch));
			subQuery.add(eq("ccategory", ccategory));
			subQuery.add(eq("csection", csection));
			subQuery.add(eq("cguarantee", cguarantee)).setProjection(property("cpremium"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_premium.class);
			mainQuery.add(propertyIn("cpremium", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

	@Override
	public Cod_category getCategory(String cbranch, String ccategory) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_premiumconfig.class);
		criteria.add(eq("cbranch", cbranch));
		criteria.add(eq("ccategory", ccategory));
		Cod_premiumconfig codPremiumConfig = genericDao.getFirstByCriteria(criteria);
		if (codPremiumConfig != null) {
			return getCategory(ccategory);
		}
		return null;
	}

	@Override
	public Cod_premium getPremium(final String cbranch, final String ccategory, final String csection, final String cguarantee, final String cpremium) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_premiumconfig.class);
		criteria.add(eq("cbranch", cbranch));
		criteria.add(eq("ccategory", ccategory));
		criteria.add(eq("csection", csection));
		criteria.add(eq("cguarantee", cguarantee));
		criteria.add(eq("cpremium", cpremium));
		Cod_premiumconfig codPremiumConfig = genericDao.getFirstByCriteria(criteria);
		if (codPremiumConfig != null) {
			return getPremium(cpremium);
		}
		return null;
	}

	@Override
	public Cod_tax getTax(String ctax) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_tax.class);
		criteria.add(eq("ctax", ctax));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_tax getTaxByPremium(String cpremium) {
		final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premium.class);
		subQuery.add(eq("cpremium", cpremium)).setProjection(property("ctax"));

		final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_tax.class);
		mainQuery.add(propertyIn("ctax", subQuery));
		return genericDao.getFirstByCriteria(mainQuery);
	}

}
