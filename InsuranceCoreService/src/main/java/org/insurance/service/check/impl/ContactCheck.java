package org.insurance.service.check.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.insurance.conf.Cod_address;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.exception.ContactException;
import org.insurance.exception.ContactException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IContactCheck;
import org.insurance.service.info.IContactInfo;

import com.google.common.base.Strings;

public class ContactCheck extends ServiceCore implements IContactCheck {

	@Inject
	private IContactInfo contactInfo;

	@Override
	public Cod_postal checkPostalCode(final String cpostal, final String city, final String ccountry) throws ContactException {
		if (Strings.isNullOrEmpty(cpostal) || Strings.isNullOrEmpty(city) || Strings.isNullOrEmpty(ccountry)) {
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_MANDATORY_CITY_POSTAL_COUNTRY);
		}
		Cod_postal codPostal = contactInfo.getZipCode(cpostal, city, ccountry);
		if (codPostal == null) {
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_CITY_POSTAL_COUNTRY, cpostal, city, ccountry);
		}
		return codPostal;

	}

	@Override
	public void checkStreets(final String street2) throws ContactException {
		if (Strings.isNullOrEmpty(street2))
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_MANDATORY_STREET2);

	}

	@Override
	public Cod_phone checkDefaultPhoneType() throws ContactException {
		Cod_phone result = contactInfo.getDefaultPhoneType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_PHONE_UNKNOWN_DEFAULT_PHONE);
		return result;
	}

	@Override
	public Cod_country checkDefaultCountry() throws ContactException {
		Cod_country result = contactInfo.getDefaultCountry();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_DEFAULT_COUNTRY);
		return result;
	}

	@Override
	public Cod_phone checkDefaultMobilePhoneType() throws ContactException {
		Cod_phone result = contactInfo.getDefaultMobilePhoneType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_PHONE_UNKNOWN_DEFAULT_MOBILE);
		return result;
	}

	@Override
	public Cod_email checkDefaultEmailType() throws ContactException {
		Cod_email result = contactInfo.getDefaultEmailType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_EMAIL_UNKNOWN_DEFAULT_EMAIL);
		return result;
	}

	@Override
	public Cod_address checkDefaultAddressType() throws ContactException {
		Cod_address result = contactInfo.getDefaultAddressType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_DEFAULT_ADDRESS);
		return result;
	}

	@Override
	public void checkAddresses(final List<Cli_address> addresses) throws ContactException {
		for (Cli_address address : addresses) {
			checkPostalCode(address.getCpostal(), address.getCity(), address.getCcountry());
			checkStreets(address.getStreet2());
		}
	}

	@Override
	public void checkEmails(final List<Cli_email> emails) throws ContactException {
		for (Cli_email cli_email : emails) {
			checkEmail(cli_email);
		}
	}

	@Override
	public void checkPhones(final List<Cli_phone> phones) throws ContactException {
		for (Cli_phone phone : phones) {
			checkPhone(phone);
		}
	}

	private void checkEmail(final Cli_email email) throws ContactException {
		Cod_email codEmail = contactInfo.getEmailType(email.getCemail());
		if (codEmail == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_EMAIL_UNKNOWN_TYPE, email.getCemail());
		if (!Strings.isNullOrEmpty(email.getEmail()) && !Strings.isNullOrEmpty(codEmail.getPattern())) {
			Pattern pattern = Pattern.compile(codEmail.getPattern());
			if (!pattern.matcher(email.getEmail()).matches()) {
				throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_EMAIL_INVALID_MAIL, email.getEmail());
			}
		}
	}

	private void checkPhone(final Cli_phone phone) throws ContactException {
		Cod_phone codPhone = contactInfo.getPhoneType(phone.getCphone());
		if (codPhone == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_PHONE_UNKNOWN_TYPE, phone.getCphone());
		if (!Strings.isNullOrEmpty(phone.getPhonenumber()) && !Strings.isNullOrEmpty(codPhone.getPattern())) {
			Pattern pattern = Pattern.compile(codPhone.getPattern());
			if (!pattern.matcher(phone.getPhonenumber()).matches()) {
				throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_PHONE_INVALID_PHONENUMBER, phone.getPhonenumber());
			}
		}
	}
}
