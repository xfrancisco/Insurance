package org.insurance.service.manager;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.exception.InsuranceException;

public interface IPersonManager {

	long insertPerson(String cuser, Cli_client client, Cli_address address) throws InsuranceException;

	long updatePerson(String cuser, Cli_client client, Cli_address address) throws InsuranceException;
}
