package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_catcli;
import org.insurance.conf.Cod_civility;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;

public interface IPersonInfo {

	Cli_client getPerson(long numcli);

	Cod_civility getCivility(String ccivil);

	Cod_catcli getCategory(String ccatcli);

	List<Cli_catcli> getCategories(long numcli);

	Cli_client getBroker(long numcli);

	Cli_client getLeader(long numcli);

	Cli_client getClient(long numcli);
}
