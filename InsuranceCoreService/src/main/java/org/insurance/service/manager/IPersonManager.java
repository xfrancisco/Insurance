package org.insurance.service.manager;

import java.util.List;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.exception.ContactException;
import org.insurance.exception.PersonException;

public interface IPersonManager {

	long insertPerson(String cuser, Cli_client client, Cli_address address, List<Cli_catcli> categories, List<Cli_phone> phones,
			List<Cli_email> emails) throws PersonException, ContactException;

	long updatePerson(String cuser, Cli_client client, Cli_address address, List<Cli_catcli> categories, List<Cli_phone> phones,
			List<Cli_email> emails) throws PersonException, ContactException;
}
