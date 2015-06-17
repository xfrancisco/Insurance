package org.mfi.service.info;

import java.util.List;

import org.mfi.conf.Cod_catcli;
import org.mfi.conf.Cod_civility;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;

public interface IPersonInfo {

	Cli_client getPerson(long numcli);

	Cod_civility getCivility(String ccivil);

	Cod_catcli getCategory(String ccatcli);

	List<Cli_catcli> getCategories(long numcli);

	Cli_client getBroker(long numcli);

	Cli_client getInsurer(long numcli);

	Cli_client getClient(long numcli);
}
