package org.insurance.common.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IPersonService;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.PersonOut;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IPersonInfo;
import org.insurance.service.manager.IPersonManager;
import org.insurance.utils.mapping.PersonMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PersonService implements IPersonService {

	static final Logger logger = Logger.getLogger(PersonService.class);

	@Inject
	private IPersonManager personManager;

	@Inject
	private IPersonInfo personInfo;

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IUserCheck userCheck;

	@Override
	public PersonOut insertPerson(final String userId, InsertPersonIn personIn) throws InsuranceException {
		userCheck.checkUser(userId);
		Cli_client client = PersonMapping.populateClient(personIn);
		Cli_address address = PersonMapping.populateAddress(personIn.getAddress());
		personManager.insertPerson(userId, client, address);
		return PersonMapping.populatePersonOut(client, address);
	}

	@Override
	public PersonOut getPerson(final String userId, long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		Cli_client client = personCheck.checkAndGetPerson(personId);
		Cli_address address = personInfo.getAddress(personId);
		return PersonMapping.populatePersonOut(client, address);
	}

	@Override
	public PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws InsuranceException {
		// Contrôles
		userCheck.checkUser(userId);
		long personId = personIn.getPersonId();
		personCheck.checkAndGetPerson(personIn.getPersonId());

		// Alimentation des beans
		Cli_client client = PersonMapping.populateClient(personIn);
		client.setNumcli(personId);
		Cli_address oldAddress = personInfo.getAddress(personId);
		Cli_address address = PersonMapping.populateAddress(personIn.getAddress());
		address.setNumcli(personId);
		address.setNumaddress(oldAddress.getNumaddress());

		// Mise à jour
		personManager.updatePerson(userId, client, address);
		return PersonMapping.populatePersonOut(client, address);
	}
}
