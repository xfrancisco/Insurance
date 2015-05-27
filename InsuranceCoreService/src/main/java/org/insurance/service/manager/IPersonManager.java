package org.insurance.service.manager;

import java.util.List;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.exception.AddressException;
import org.insurance.exception.PersonException;

public interface IPersonManager {

	long insertPerson(String cuser, Cli_client client, Cli_address address, List<Cli_catcli> categories) throws PersonException, AddressException;

	long updatePerson(String cuser, Cli_client client, Cli_address address, List<Cli_catcli> categories) throws PersonException, AddressException;
}
