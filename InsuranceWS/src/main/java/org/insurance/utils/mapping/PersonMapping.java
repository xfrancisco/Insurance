package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.in.AddressIn;
import org.insurance.in.ClientCategoryIn;
import org.insurance.in.InsertPersonIn;
import org.insurance.out.AddressOut;
import org.insurance.out.PersonCategoryOut;
import org.insurance.out.PersonOut;

public final class PersonMapping {

	public static Cli_client populateClient(InsertPersonIn personIn) {
		Cli_client result = new Cli_client();
		if (personIn != null) {
			result.setCcivil(personIn.getCivility());
			result.setFirstname(personIn.getFirstName());
			result.setName(personIn.getLastName());
			result.setCompanyname(personIn.getCompanyName());
			result.setCompanyid(personIn.getCompanyId());
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

	public static PersonOut populatePersonOut(Cli_client client, Cli_address address, List<Cli_catcli> categories, List<Cli_phone> phones,
			List<Cli_email> emails) {
		PersonOut result = new PersonOut();
		if (client != null) {

			result.setCivility(client.getCcivil());
			result.setCompanyId(client.getCompanyid());
			result.setCompanyName(client.getCompanyname());
			result.setFirstName(client.getFirstname());
			result.setLastName(client.getName());
			result.setPersonId(client.getNumcli());

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

			if (categories != null) {
				List<PersonCategoryOut> categoriesOut = new ArrayList<PersonCategoryOut>();
				for (Cli_catcli cliCatcli : categories) {
					PersonCategoryOut out = new PersonCategoryOut();
					out.setCategoryId(cliCatcli.getCcatcli());
					categoriesOut.add(out);
				}
				result.setCategories(categoriesOut);
			}

			if (emails != null) {
				for (Cli_email cliEmail : emails) {
					result.setEmail(cliEmail.getEmail());
				}
			}

			if (phones != null) {
				for (Cli_phone cliPhone : phones) {
					if (cliPhone.isMobile())
						result.setMobile(cliPhone.getPhonenumber());
					else
						result.setPhone(cliPhone.getPhonenumber());
				}
			}
		}
		return result;
	}

	public static List<PersonOut> populatePersonOut(Collection<Cli_client> clients) {
		List<PersonOut> result = new ArrayList<PersonOut>();
		for (Cli_client client : clients) {
			result.add(populatePersonOut(client, null, null, null, null));
		}
		return result;

	}

	public static List<Cli_catcli> populateCategories(List<ClientCategoryIn> categories) {
		List<Cli_catcli> result = new ArrayList<Cli_catcli>();
		for (ClientCategoryIn clientCategoryIn : categories) {
			Cli_catcli tmp = new Cli_catcli();
			tmp.setCcatcli(clientCategoryIn.getCategoryId());
			result.add(tmp);
		}
		return result;
	}

	public static List<Cli_phone> populatePhones(String mobilePhoneNumber, String phoneNumber, Cod_phone codPhoneMobile, Cod_phone codPhone) {
		List<Cli_phone> result = new ArrayList<Cli_phone>();
		Cli_phone mobilePhoneDto = new Cli_phone(codPhoneMobile.getCphone(), mobilePhoneNumber);
		result.add(mobilePhoneDto);

		Cli_phone phoneDto = new Cli_phone(codPhone.getCphone(), phoneNumber);
		result.add(phoneDto);
		return result;
	}

	public static List<Cli_email> populateEmail(String email, Cod_email defaultEmailType) {
		List<Cli_email> result = new ArrayList<Cli_email>();
		Cli_email emailDto = new Cli_email(defaultEmailType.getCemail(), email);
		result.add(emailDto);
		return result;
	}
}
