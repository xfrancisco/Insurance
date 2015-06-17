package org.mfi.service.manager;

import java.util.List;

import org.mfi.data.Cli_address;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;
import org.mfi.data.Cli_email;
import org.mfi.data.Cli_phone;
import org.mfi.exception.ContactException;
import org.mfi.exception.PersonException;

public interface IPersonManager {

	long insertPerson(String cuser, Cli_client client, List<Cli_address> addresses, List<Cli_catcli> categories, List<Cli_phone> phones,
			List<Cli_email> emails) throws PersonException, ContactException;

	long updatePerson(long numcli, String cuser, Cli_client client, List<Cli_address> address, List<Cli_catcli> categories, List<Cli_phone> phones,
			List<Cli_email> emails) throws PersonException, ContactException;
}
