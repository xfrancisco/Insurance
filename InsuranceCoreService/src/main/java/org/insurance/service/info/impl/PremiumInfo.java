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
	public Cod_branch getBranch(String cbranch) {
		return genericDao.get(Cod_branch.class, cbranch);
	}

	@Override
	public Cod_category getCategory(String ccategory) {
		return genericDao.get(Cod_category.class, ccategory);
	}

	@Override
	public Cod_section getSection(String csection) {
		return genericDao.get(Cod_section.class, csection);
	}

	@Override
	public Cod_guarantee getGuarantee(String cguarantee) {
		return genericDao.get(Cod_guarantee.class, cguarantee);
	}

	@Override
	public Cod_premium getPremium(String cpremium) {
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
	public List<Cod_section> getSections(final String ccategory) {
		if (Strings.isNullOrEmpty(ccategory))
			return genericDao.getList(Cod_section.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("ccategory", ccategory)).setProjection(property("csection"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_section.class);
			mainQuery.add(propertyIn("csection", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

	@Override
	public List<Cod_guarantee> getGuarantees(final String csection) {
		if (Strings.isNullOrEmpty(csection))
			return genericDao.getList(Cod_guarantee.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("csection", csection)).setProjection(property("cguarantee"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_guarantee.class);
			mainQuery.add(propertyIn("cguarantee", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

	@Override
	public List<Cod_premium> getPremiums(final String cguarantee) {
		if (Strings.isNullOrEmpty(cguarantee))
			return genericDao.getList(Cod_premium.class);
		else {
			final DetachedCriteria subQuery = DetachedCriteria.forClass(Cod_premiumconfig.class);
			subQuery.add(eq("cguarantee", cguarantee)).setProjection(property("cpremium"));

			final DetachedCriteria mainQuery = DetachedCriteria.forClass(Cod_premium.class);
			mainQuery.add(propertyIn("cpremium", subQuery));
			return genericDao.getByCriteria(mainQuery);
		}
	}

}
