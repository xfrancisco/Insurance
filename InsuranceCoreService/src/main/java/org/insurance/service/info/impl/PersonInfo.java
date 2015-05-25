package org.insurance.service.info.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_catcli;
import org.insurance.conf.Cod_civility;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IPersonInfo;
import org.springframework.stereotype.Service;

@Service
public class PersonInfo extends ServiceCore implements IPersonInfo {

	@Override
	public Cli_client getPerson(long numcli) {
		return genericDao.get(Cli_client.class, numcli);
	}

	@Override
	public Cli_address getAddress(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_civility getCivility(final String ccivil) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_civility.class, ccivil);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_catcli getCategory(final String ccatcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_catcli.class, ccatcli);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

}
