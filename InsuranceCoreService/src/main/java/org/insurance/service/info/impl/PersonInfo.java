package org.insurance.service.info.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

}
