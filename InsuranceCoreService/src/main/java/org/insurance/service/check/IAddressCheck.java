package org.insurance.service.check;

import org.insurance.exception.AddressException;

public interface IAddressCheck {

	void checkPostalCode(String cpostal, String city, String ccountry) throws AddressException;

	void checkStreets(String street2) throws AddressException;
}
