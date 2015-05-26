package org.insurance.service.transactional.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.movements.person.NewAddressMovement;
import org.insurance.movements.person.NewPersonMovement;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IMovementOperation;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonOperation extends ServiceCore implements IPersonOperation {
	@Inject
	private IMovementOperation movementOperation;

	@Override
	public Long insertClient(final String cuser, final Cli_client client) {
		client.setCusercre(cuser);
		client.setCreationDate(dbHelper.getNow());
		Long numcli = (Long) genericDao.save(client);
		NewPersonMovement movement = new NewPersonMovement(client.getCcivil(), client.getName(), client.getFirstname(), client.getCompanyname(),
				client.getCompanyid());
		movementOperation.insertMovement(numcli, null, cuser, movement);
		return numcli;
	}

	@Override
	public Long insertAddress(final String cuser, Cli_address address) {
		address.setCusercre(cuser);
		address.setCreationDate(dbHelper.getNow());
		Long numaddress = (Long) genericDao.save(address);
		NewAddressMovement movement = new NewAddressMovement(address.getStreet1(), address.getStreet2(), address.getStreet3(), address.getStreet4(),
				address.getCpostal(), address.getCity(), address.getCcountry());
		movementOperation.insertMovement(address.getNumcli(), null, cuser, movement);
		return numaddress;
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
		genericDao.merge(address);
		return address.getNumaddress();
	}

}
