package org.insurance.service.check;

import org.insurance.data.Cli_client;
import org.insurance.exception.PersonException;

public interface IPersonCheck {
	void checkCivility(String ccivil) throws PersonException;

	void checkName(String name, String companyname, String companyid) throws PersonException;

	void checkCategory(String ccatcli) throws PersonException;

	Cli_client checkAndGetPerson(long personId) throws PersonException;

}