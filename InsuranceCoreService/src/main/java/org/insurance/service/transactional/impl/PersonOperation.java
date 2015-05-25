package org.insurance.service.transactional.impl;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonOperation extends ServiceCore implements IPersonOperation {

	@Override
	public Long insertClient(final String cuser, final Cli_client client) {
		client.setCusercre(cuser);
		client.setCreationDate(dbHelper.getNow());
		return (Long) genericDao.save(client);
	}

	@Override
	public Long insertAddress(final String cuser, Cli_address address) {
		address.setCusercre(cuser);
		address.setCreationDate(dbHelper.getNow());
		return (Long) genericDao.save(address);
	}

	@Override
	public Long updateClient(final String cuser, final Cli_client client) {
		client.setCusermod(cuser);
		client.setModifDate(dbHelper.getNow());
		genericDao.saveOrUpdate(client);
		return client.getNumcli();
	}

	@Override
	public Long updateAddress(final String cuser, Cli_address address) {
		address.setCusermod(cuser);
		address.setModifDate(dbHelper.getNow());
		genericDao.saveOrUpdate(address);
		return address.getNumaddress();
	}

}
