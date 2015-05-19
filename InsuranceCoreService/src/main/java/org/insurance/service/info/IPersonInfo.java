package org.insurance.service.info;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;

public interface IPersonInfo {

	Cli_client getPerson(long numcli);

	Cli_address getAddress(long numcli);
}
