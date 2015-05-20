package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_postal;
import org.insurance.exception.AddressException;
import org.insurance.exception.AddressException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IAddressCheck;
import org.insurance.service.info.IAddressInfo;

import com.google.common.base.Strings;

public class AddressCheck extends ServiceCore implements IAddressCheck {

	@Inject
	private IAddressInfo addressInfo;

	@Override
	public void checkPostalCode(String cpostal, String city, String ccountry) throws AddressException {
		if (Strings.isNullOrEmpty(cpostal) || Strings.isNullOrEmpty(city) || Strings.isNullOrEmpty(ccountry)) {
			throw new AddressException(ErrorCode.ERR_BIZ_ADDRESS_MANDATORY_CITY_POSTAL_COUNTRY);
		}
		Cod_postal codPostal = addressInfo.getZipCode(cpostal, city, ccountry);
		if (codPostal == null) {
			throw new AddressException(ErrorCode.ERR_BIZ_ADDRESS_UNKNOWN_CITY_POSTAL_COUNTRY, cpostal, city, ccountry);
		}

	}

	@Override
	public void checkStreets(String street2) throws AddressException {
		if (Strings.isNullOrEmpty(street2))
			throw new AddressException(ErrorCode.ERR_BIZ_ADDRESS_MANDATORY_STREET2);

	}

}
