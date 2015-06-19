package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.mfi.common.IPersonService;
import org.mfi.conf.Cod_address;
import org.mfi.conf.Cod_email;
import org.mfi.conf.Cod_phone;
import org.mfi.data.Cli_address;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;
import org.mfi.data.Cli_email;
import org.mfi.data.Cli_phone;
import org.mfi.exception.MfcException;
import org.mfi.in.InsertPersonIn;
import org.mfi.in.UpdatePersonIn;
import org.mfi.out.person.AddressHistoryOut;
import org.mfi.out.person.EmailHistoryOut;
import org.mfi.out.person.PersonOut;
import org.mfi.out.person.PhoneHistoryOut;
import org.mfi.service.check.IContactCheck;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IContactInfo;
import org.mfi.service.info.IPersonInfo;
import org.mfi.service.manager.IPersonManager;
import org.mfi.utils.mapping.PersonMapping;
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
	public PersonOut insertPerson(final String userId, InsertPersonIn personIn) throws MfcException {
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
	public PersonOut getPerson(final String userId, long personId) throws MfcException {
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
	public PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws MfcException {
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
	public List<AddressHistoryOut> getAddressHistory(String userId, Long personId) throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_address> addresses = contactInfo.getOldAddresses(personId);
		return PersonMapping.populateAddressHistoryOut(addresses);
	}

	@Override
	public List<PhoneHistoryOut> getPhoneHistory(String userId, Long personId) throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_phone> phones = contactInfo.getOldPhones(personId);
		return PersonMapping.populatePhonesHistoryOut(phones);
	}

	@Override
	public List<EmailHistoryOut> getEmailHistory(String userId, Long personId) throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		List<Cli_email> emails = contactInfo.getOldEmails(personId);
		return PersonMapping.getEmailHistoryOut(emails);
	}
}
