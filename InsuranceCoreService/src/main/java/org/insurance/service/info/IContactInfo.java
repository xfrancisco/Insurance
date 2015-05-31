package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_address;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;

public interface IContactInfo {

	Cli_address getAddressByType(long numcli, String caddress);

	Cod_postal getZipCode(String cpostal, String city, String ccountry);

	Cod_phone getDefaultPhoneType();

	Cod_phone getDefaultMobilePhoneType();

	Cod_email getDefaultEmailType();

	List<Cli_phone> getPhones(long numcli);

	List<Cli_email> getEmails(long numcli);

	Cli_email getEmailByType(long numcli, String cemail);

	Cli_phone getPhoneByType(long numcli, String cphone);

	List<Cli_address> getOldAddresses(long numcli);

	List<Cli_phone> getOldPhones(long numcli);

	List<Cli_email> getOldEmails(long numcli);

	Cod_address getDefaultAddressType();

	Cod_phone getPhoneType(String cphone);

	Cod_email getEmailType(String cemail);

	Cod_country getDefaultCountry();

	List<Cod_postal> getZipCodesByZipCode(String cpostal, String ccountry);

	List<Cod_postal> getZipCodesByCity(String city, String ccountry);
}
