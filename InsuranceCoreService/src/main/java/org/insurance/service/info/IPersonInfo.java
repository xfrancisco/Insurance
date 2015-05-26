package org.insurance.service.info;

import org.insurance.conf.Cod_catcli;
import org.insurance.conf.Cod_civility;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.movements.person.ModAddressMovement;
import org.insurance.movements.person.ModPersonMovement;

public interface IPersonInfo {

	Cli_client getPerson(long numcli);

	Cli_address getAddress(long numcli);

	Cod_civility getCivility(String ccivil);

	Cod_catcli getCategory(String ccatcli);

	ModPersonMovement hasClientChanged(Cli_client client);

	ModAddressMovement hasAddressChanged(Cli_address address);
}
