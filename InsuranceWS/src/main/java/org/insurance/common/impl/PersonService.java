package org.insurance.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IPersonService;
import org.insurance.conf.Cod_address;
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
import org.insurance.out.AddressHistoryOut;
import org.insurance.out.EmailHistoryOut;
import org.insurance.out.PersonOut;
import org.insurance.out.PhoneHistoryOut;
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

		Cod_phone defaultPhoneType = contactCheck.checkDefaultPhoneType();
		Cod_phone defaultMobilePhoneType = contactCheck.checkDefaultMobilePhoneType();
		Cod_email defaultEmailType = contactCheck.checkDefaultEmailType();
		Cod_address defaultAddressType = contactCheck.checkDefaultAddressType();

		Cli_client client = PersonMapping.populateClient(personIn);
		List<Cli_address> addresses = PersonMapping.populateAddress(personIn.getAddress(), defaultAddressType);
		List<Cli_catcli> categories = PersonMapping.populateCategories(personIn.getCategories());
		List<Cli_phone> phones = PersonMapping.populatePhones(personIn.getMobile(), personIn.getPhone(), defaultMobilePhoneType, defaultPhoneType);
		List<Cli_email> emails = PersonMapping.populateEmail(personIn.getEmail(), defaultEmailType);

		personManager.insertPerson(userId, client, addresses, categories, phones, emails);

		return getPerson(userId, client.getNumcli());
	}

	@Override
	public PersonOut getPerson(final String userId, long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		Cli_client client = personCheck.checkPerson(personId);
		Cod_address defaultAddressType = contactCheck.checkDefaultAddressType();
		Cli_address address = contactInfo.getAddressByType(personId, defaultAddressType.getCaddress());
		List<Cli_catcli> categories = personInfo.getCategories(personId);
		List<Cli_phone> phones = contactInfo.getPhones(personId);
		List<Cli_email> emails = contactInfo.getEmails(personId);
		return PersonMapping.populatePersonOut(client, address, categories, phones, emails);
	}

	@Override
	public PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws InsuranceException {
		// Contrôles
		userCheck.checkUser(userId);
		long personId = personIn.getPersonId();
		personCheck.checkPerson(personId);

		Cod_phone defaultPhoneType = contactCheck.checkDefaultPhoneType();
		Cod_phone defaultMobilePhoneType = contactCheck.checkDefaultMobilePhoneType();
		Cod_email defaultEmailType = contactCheck.checkDefaultEmailType();
		Cod_address defaultAddressType = contactCheck.checkDefaultAddressType();

		// Alimentation des beans
		Cli_client client = PersonMapping.populateClient(personIn);
		List<Cli_address> addresses = PersonMapping.populateAddress(personIn.getAddress(), defaultAddressType);
		List<Cli_catcli> categories = PersonMapping.populateCategories(personIn.getCategories());
		List<Cli_phone> phones = PersonMapping.populatePhones(personIn.getMobile(), personIn.getPhone(), defaultMobilePhoneType, defaultPhoneType);
		List<Cli_email> emails = PersonMapping.populateEmail(personIn.getEmail(), defaultEmailType);

		// Mise à jour
		personManager.updatePerson(personId, userId, client, addresses, categories, phones, emails);
		return getPerson(userId, personId);
	}

	@Override
	public List<AddressHistoryOut> getAddressHistory(String userId, Long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_address> addresses = contactInfo.getOldAddresses(personId);
		return PersonMapping.populateAddressHistoryOut(addresses);
	}

	@Override
	public List<PhoneHistoryOut> getPhoneHistory(String userId, Long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_phone> phones = contactInfo.getOldPhones(personId);
		return PersonMapping.populatePhonesHistoryOut(phones);
	}

	@Override
	public List<EmailHistoryOut> getEmailHistory(String userId, Long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_email> emails = contactInfo.getOldEmails(personId);
		return PersonMapping.getEmailHistoryOut(emails);
	}
}
