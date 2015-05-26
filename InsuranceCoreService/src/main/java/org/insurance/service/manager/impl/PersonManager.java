package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.exception.AddressException;
import org.insurance.exception.PersonException;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IAddressCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.info.IPersonInfo;
import org.insurance.service.manager.IPersonManager;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonManager extends ServiceCore implements IPersonManager {

	@Inject
	private IPersonOperation personOperation;

	@Inject
	private IAddressCheck addressCheck;

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IPersonInfo personInfo;

	@Override
	public long insertPerson(final String cuser, final Cli_client client, Cli_address address) throws PersonException, AddressException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		addressCheck.checkPostalCode(address.getCpostal(), address.getCity(), address.getCcountry());
		addressCheck.checkStreets(address.getStreet2());
		long numcli = personOperation.insertClient(cuser, client);
		address.setNumcli(numcli);
		personOperation.insertAddress(cuser, address);
		return numcli;

	}

	@Override
	public long updatePerson(String cuser, Cli_client client, Cli_address address) throws PersonException, AddressException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		addressCheck.checkPostalCode(address.getCpostal(), address.getCity(), address.getCcountry());
		addressCheck.checkStreets(address.getStreet2());
		long numcli = client.getNumcli();
		if (personInfo.hasClientChanged(client)) {
			personOperation.updateClient(cuser, client);
		}
		if (personInfo.hasAddressChanged(address)) {
			personOperation.updateAddress(cuser, address);
		}
		return numcli;
	}

}