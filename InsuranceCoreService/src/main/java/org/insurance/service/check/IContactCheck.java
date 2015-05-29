package org.insurance.service.check;

import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.exception.ContactException;

public interface IContactCheck {

	void checkPostalCode(String cpostal, String city, String ccountry) throws ContactException;

	void checkStreets(String street2) throws ContactException;

	Cod_phone checkAndGetDefaultPhoneType() throws ContactException;

	Cod_phone checkAndGetDefaultMobilePhoneType() throws ContactException;

	Cod_email checkAndGetDefaultEmailType() throws ContactException;
}
