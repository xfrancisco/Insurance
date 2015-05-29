package org.insurance.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IPersonService;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.PersonOut;
import org.insurance.service.check.IContactCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IContactInfo;
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
	private IContactInfo contactInfo;

	@Inject
	private IContactCheck contactCheck;

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IUserCheck userCheck;

	@Override
	public PersonOut insertPerson(final String userId, InsertPersonIn personIn) throws InsuranceException {
		userCheck.checkUser(userId);
		Cli_client client = PersonMapping.populateClient(personIn);
		Cli_address address = PersonMapping.populateAddress(personIn.getAddress());
		List<Cli_catcli> categories = PersonMapping.populateCategories(personIn.getCategories(), null);
		Cod_phone defaultPhoneType = contactCheck.checkAndGetDefaultPhoneType();
		Cod_phone defaultMobilePhoneType = contactCheck.checkAndGetDefaultMobilePhoneType();
		List<Cli_phone> phones = PersonMapping.populatePhones(personIn.getMobile(), personIn.getPhone(), defaultMobilePhoneType, defaultPhoneType);
		Cod_email defaultEmailType = contactCheck.checkAndGetDefaultEmailType();
		List<Cli_email> emails = PersonMapping.populateEmail(personIn.getEmail(), defaultEmailType);
		personManager.insertPerson(userId, client, address, categories, phones, emails);
		return PersonMapping.populatePersonOut(client, address, categories);
	}

	@Override
	public PersonOut getPerson(final String userId, long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		Cli_client client = personCheck.checkAndGetPerson(personId);
		Cli_address address = contactInfo.getAddress(personId);
		List<Cli_catcli> categories = personInfo.getCategories(personId);
		return PersonMapping.populatePersonOut(client, address, categories);
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
		Cli_address oldAddress = contactInfo.getAddress(personId);
		Cli_address address = PersonMapping.populateAddress(personIn.getAddress());
		address.setNumcli(personId);
		address.setNumaddress(oldAddress.getNumaddress());

		List<Cli_catcli> categories = PersonMapping.populateCategories(personIn.getCategories(), personId);

		Cod_phone defaultPhoneType = contactCheck.checkAndGetDefaultPhoneType();
		Cod_phone defaultMobilePhoneType = contactCheck.checkAndGetDefaultMobilePhoneType();
		List<Cli_phone> phones = PersonMapping.populatePhones(personIn.getMobile(), personIn.getPhone(), defaultMobilePhoneType, defaultPhoneType);
		Cod_email defaultEmailType = contactCheck.checkAndGetDefaultEmailType();
		List<Cli_email> emails = PersonMapping.populateEmail(personIn.getEmail(), defaultEmailType);

		// Mise à jour
		personManager.updatePerson(userId, client, address, categories, phones, emails);
		return PersonMapping.populatePersonOut(client, address, categories);
	}
}
