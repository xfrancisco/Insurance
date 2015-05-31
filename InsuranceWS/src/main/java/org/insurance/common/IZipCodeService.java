package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.ZipCodeIn;
import org.insurance.out.ZipCodeOut;

public interface IZipCodeService {

	List<ZipCodeOut> getZipCodesByZipCode(String userId, String zipCode, String country) throws InsuranceException;

	List<ZipCodeOut> getZipCodesByCity(String userId, String city, String country) throws InsuranceException;

	ZipCodeOut createZipCode(String userId, ZipCodeIn zipCodeIn) throws InsuranceException;;

}
