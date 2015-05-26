package org.insurance.service.info.impl;

import java.util.List;

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
	public boolean hasClientChanged(Cli_client client) {
		Cli_client oldClient = getPerson(client.getNumcli());
		List<String> changes = client.getChanges(oldClient);
		client.setCusercre(oldClient.getCusercre());
		client.setCreationDate(oldClient.getCreationDate());
		return !changes.isEmpty();
	}

	@Override
	public boolean hasAddressChanged(Cli_address address) {
		Cli_address oldAddress = getAddress(address.getNumaddress());
		List<String> changes = address.getChanges(oldAddress);
		address.setCreationDate(oldAddress.getCreationDate());
		address.setCusercre(oldAddress.getCusercre());
		return !changes.isEmpty();
	}

}
