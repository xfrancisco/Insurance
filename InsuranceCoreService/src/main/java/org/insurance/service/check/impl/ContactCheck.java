package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
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
	public void checkPostalCode(String cpostal, String city, String ccountry) throws ContactException {
		if (Strings.isNullOrEmpty(cpostal) || Strings.isNullOrEmpty(city) || Strings.isNullOrEmpty(ccountry)) {
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_MANDATORY_CITY_POSTAL_COUNTRY);
		}
		Cod_postal codPostal = contactInfo.getZipCode(cpostal, city, ccountry);
		if (codPostal == null) {
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_UNKNOWN_CITY_POSTAL_COUNTRY, cpostal, city, ccountry);
		}

	}

	@Override
	public void checkStreets(String street2) throws ContactException {
		if (Strings.isNullOrEmpty(street2))
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_MANDATORY_STREET2);

	}

	@Override
	public Cod_phone checkAndGetDefaultPhoneType() throws ContactException {
		Cod_phone result = contactInfo.getDefaultPhoneType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_TEL_UNKNOWN_DEFAULT_PHONE);
		return result;
	}

	@Override
	public Cod_phone checkAndGetDefaultMobilePhoneType() throws ContactException {
		Cod_phone result = contactInfo.getDefaultMobilePhoneType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_TEL_UNKNOWN_DEFAULT_MOBILE);
		return result;
	}

	@Override
	public Cod_email checkAndGetDefaultEmailType() throws ContactException {
		Cod_email result = contactInfo.getDefaultEmailType();
		if (result == null)
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_EMAIL_UNKNOWN_DEFAULT_EMAIL);
		return result;
	}
}
