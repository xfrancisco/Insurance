package org.insurance.common;

import org.insurance.exception.InsuranceException;
import org.insurance.out.ZipCodeOut;

public interface IZipCodeService {

	ZipCodeOut getZipCodesByZipCode(String userId, String zipCode, String country) throws InsuranceException;

	ZipCodeOut getZipCodesByCity(String userId, String city, String country) throws InsuranceException;;



}
