package org.mfi.service.check;

import java.util.List;

import org.mfi.conf.Cod_catcli;
import org.mfi.conf.Cod_civility;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;
import org.mfi.exception.PersonException;

public interface IPersonCheck {
	Cod_civility checkCivility(String ccivil) throws PersonException;

	void checkName(String name, String companyname, String companyid) throws PersonException;

	Cod_catcli checkCategory(String ccatcli) throws PersonException;

	Cli_client checkPerson(long personId) throws PersonException;

	void checkCategories(List<Cli_catcli> categories) throws PersonException;

	Cli_client checkBroker(long numclibroker) throws PersonException;

	Cli_client checkClient(long numcli) throws PersonException;

	Cli_client checkInsurer(long numclileader) throws PersonException;

}
