package org.insurance.service.check;

import java.util.List;

import org.insurance.conf.Cod_address;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.exception.ContactException;

public interface IContactCheck {

	void checkPostalCode(String cpostal, String city, String ccountry) throws ContactException;

	void checkStreets(String street2) throws ContactException;

	Cod_phone checkAndGetDefaultPhoneType() throws ContactException;

	Cod_phone checkAndGetDefaultMobilePhoneType() throws ContactException;

	Cod_email checkAndGetDefaultEmailType() throws ContactException;

	Cod_address checkAndGetDefaultAddressType() throws ContactException;

	void checkAddresses(List<Cli_address> addresses) throws ContactException;

	void checkEmails(List<Cli_email> emails) throws ContactException;

	void checkPhones(List<Cli_phone> phones) throws ContactException;

	Cod_country checkAndGetDefaultCountry() throws ContactException;;
}
