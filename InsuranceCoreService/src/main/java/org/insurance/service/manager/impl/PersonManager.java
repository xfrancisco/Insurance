package org.insurance.service.manager.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.exception.ContactException;
import org.insurance.exception.PersonException;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IContactCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.manager.IPersonManager;
import org.insurance.service.transactional.IContactOperation;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonManager extends ServiceCore implements IPersonManager {

	@Inject
	private IPersonOperation personOperation;

	@Inject
	private IContactOperation contactOperation;

	@Inject
	private IContactCheck contactCheck;

	@Inject
	private IPersonCheck personCheck;

	@Override
	public long insertPerson(final String cuser, final Cli_client client, List<Cli_address> addresses, List<Cli_catcli> categories,
			List<Cli_phone> phones, List<Cli_email> emails) throws PersonException, ContactException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		contactCheck.checkAddresses(addresses);
		personCheck.checkCategories(categories);
		contactCheck.checkPhones(phones);
		contactCheck.checkEmails(emails);

		long numcli = personOperation.insertClient(cuser, client);

		contactOperation.insertAddresses(numcli, cuser, addresses);
		personOperation.insertCategories(numcli, cuser, categories);
		contactOperation.insertPhones(numcli, phones, cuser);
		contactOperation.insertEmails(numcli, emails, cuser);
		return numcli;
	}

	@Override
	public long updatePerson(long numcli, String cuser, Cli_client client, List<Cli_address> addresses, List<Cli_catcli> categories,
			List<Cli_phone> phones, List<Cli_email> emails) throws PersonException, ContactException {
		personCheck.checkCivility(client.getCcivil());
		personCheck.checkName(client.getName(), client.getCompanyname(), client.getCompanyid());
		contactCheck.checkAddresses(addresses);
		contactCheck.checkPhones(phones);
		contactCheck.checkEmails(emails);
		personCheck.checkCategories(categories);

		personOperation.updateClient(numcli, cuser, client);
		contactOperation.updateAddresses(numcli, cuser, addresses);
		contactOperation.updatePhones(numcli, phones, cuser);
		contactOperation.updateEmails(numcli, emails, cuser);
		personOperation.updateCategories(numcli, cuser, categories);
		return numcli;
	}

}