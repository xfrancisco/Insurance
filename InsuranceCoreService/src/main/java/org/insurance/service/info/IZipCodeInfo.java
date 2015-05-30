package org.insurance.service.info;

import org.insurance.conf.Cod_postal;


public interface IZipCodeInfo {

	Cod_postal getZipCodesByZipCode(String cpostal, String ccountry);

	Cod_postal getZipCodesByCity(String city, String ccountry);

	

}
