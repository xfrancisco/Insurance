package org.insurance.service.info;

import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;

public interface IAddressInfo {

	Cli_client getPerson(long numcli);

	Cli_address getAddress(long numcli);

	Cod_postal getZipCode(String cpostal, String city, String ccountry);
}
