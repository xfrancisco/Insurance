package org.insurance.service.manager.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.exception.AddressException;
import org.insurance.exception.PersonException;
import org.insurance.movements.person.ModAddressMovement;
import org.insurance.movements.person.ModPersonMovement;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IAddressCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.info.IPersonInfo;
import org.insurance.service.manager.IPersonManager;
import org.insurance.service.transactional.IMovementOperation;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonManager extends ServiceCore implements IPersonManager {

	@Inject
	private IPersonOperation personOperation;

	@Inject
	private IMovementOperation movementOperation;

	@Inject
	private IAddressCheck addressCheck;

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IPersonInfo personInfo;

	@Override
	public long insertPerson(final String cuser, final Cli_client client, Cli_address address, List<Cli_catcli> categories) throws PersonException,
			AddressException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		addressCheck.checkPostalCode(address.getCpostal(), address.getCity(), address.getCcountry());
		addressCheck.checkStreets(address.getStreet2());
		for (Cli_catcli cliCatcli : categories) {
			personCheck.checkCategory(cliCatcli.getCcatcli());
		}
		long numcli = personOperation.insertClient(cuser, client);
		address.setNumcli(numcli);
		personOperation.insertAddress(cuser, address);
		for (Cli_catcli cliCatcli : categories) {
			cliCatcli.setNumcli(numcli);
		}
		personOperation.insertCategories(cuser, categories);
		return numcli;
	}

	@Override
	public long updatePerson(String cuser, Cli_client client, Cli_address address, List<Cli_catcli> categories) throws PersonException,
			AddressException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		addressCheck.checkPostalCode(address.getCpostal(), address.getCity(), address.getCcountry());
		addressCheck.checkStreets(address.getStreet2());
		long numcli = client.getNumcli();
		ModPersonMovement modPersonMovement = personInfo.hasClientChanged(client);
		if (modPersonMovement != null) {
			personOperation.updateClient(cuser, client);
			movementOperation.insertMovement(client.getNumcli(), null, cuser, modPersonMovement);
		}
		ModAddressMovement modAddressMovement = personInfo.hasAddressChanged(address);
		if (modAddressMovement != null) {
			personOperation.updateAddress(cuser, address);
			movementOperation.insertMovement(client.getNumcli(), null, cuser, modAddressMovement);
		}
		personOperation.updateCategories(numcli, cuser, categories);
		return numcli;
	}

}