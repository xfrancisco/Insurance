package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.service.ServiceCore;
import org.insurance.service.manager.IPersonManager;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonManager extends ServiceCore implements IPersonManager {

	@Inject
	private IPersonOperation personOperation;

	@Override
	public long insertPerson(final Cli_client client, Cli_address address) {
		long numcli = personOperation.insertClient(client);
		address.setNumcli(numcli);
		long numaddress = personOperation.insertAddress(address);
		return numcli;

	}

}