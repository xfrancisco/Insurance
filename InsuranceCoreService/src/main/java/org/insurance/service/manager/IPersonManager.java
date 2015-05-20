package org.insurance.service.manager;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.exception.InsuranceException;

public interface IPersonManager {

	long insertPerson(Cli_client client, Cli_address address) throws InsuranceException;
}
