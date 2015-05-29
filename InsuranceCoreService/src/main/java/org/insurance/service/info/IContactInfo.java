package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;

public interface IContactInfo {

	Cli_address getAddress(long numcli);

	Cod_postal getZipCode(String cpostal, String city, String ccountry);

	Cod_phone getDefaultPhoneType();

	Cod_phone getDefaultMobilePhoneType();

	Cod_email getDefaultEmailType();

	List<Cli_phone> getPhones(long numcli);

	List<Cli_email> getEmails(long numcli);

	Cli_email getEmailByType(long numcli, String cemail);

	Cli_phone getPhoneByType(long numcli, String cphone);
}
