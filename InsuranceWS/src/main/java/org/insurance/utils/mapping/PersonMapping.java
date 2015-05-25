package org.insurance.utils.mapping;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.in.AddressIn;
import org.insurance.in.InsertPersonIn;
import org.insurance.out.AddressOut;
import org.insurance.out.PersonOut;

public final class PersonMapping {

	public static Cli_client populateClient(InsertPersonIn personIn) {
		Cli_client result = new Cli_client();
		if (personIn != null) {
			result.setCcivil(personIn.getCivility());
			result.setFirstname(personIn.getFirstName());
			result.setName(personIn.getName());
			result.setCompanyname(personIn.getCompanyName());
			result.setCompanyid(personIn.getCompanyId());
			result.setCcatcli(personIn.getPersonCategory());
		}
		return result;
	}

	public static Cli_address populateAddress(AddressIn addressIn) {
		Cli_address result = new Cli_address();
		if (addressIn != null) {
			result.setStreet1(addressIn.getStreet1());
			result.setStreet2(addressIn.getStreet2());
			result.setStreet3(addressIn.getStreet3());
			result.setStreet4(addressIn.getStreet4());
			result.setCpostal(addressIn.getZipCode());
			result.setCity(addressIn.getCity());
			result.setCcountry(addressIn.getCountry());
		}
		return result;
	}

	public static PersonOut populatePersonOut(Cli_client client, Cli_address address) {
		PersonOut result = new PersonOut();
		if (client != null) {

			result.setCcivil(client.getCcivil());
			result.setCompanyId(client.getCompanyid());
			result.setCompanyName(client.getCompanyname());
			result.setFirstName(client.getFirstname());
			result.setName(client.getName());
			result.setPersonId(client.getNumcli());
			result.setPersonCategory(client.getCcatcli());

			if (address != null) {
				AddressOut resultAddress = new AddressOut();
				resultAddress.setStreet1(address.getStreet1());
				resultAddress.setStreet2(address.getStreet2());
				resultAddress.setStreet3(address.getStreet3());
				resultAddress.setStreet4(address.getStreet4());
				resultAddress.setCity(address.getCity());
				resultAddress.setCountry(address.getCcountry());
				resultAddress.setZipCode(address.getCpostal());
				result.setAddress(resultAddress);
			}
		}

		return result;
	}
}
