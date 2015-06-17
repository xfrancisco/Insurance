package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.ZipCodeIn;
import org.mfi.out.ZipCodeOut;

public interface IZipCodeService {

	List<ZipCodeOut> getZipCodesByZipCode(String userId, String zipCode, String country) throws MfcException;

	List<ZipCodeOut> getZipCodesByCity(String userId, String city, String country) throws MfcException;

	ZipCodeOut createZipCode(String userId, ZipCodeIn zipCodeIn) throws MfcException;;

}
