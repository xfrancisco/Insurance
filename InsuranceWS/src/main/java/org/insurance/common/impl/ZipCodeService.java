package org.insurance.common.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IZipCodeService;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_postal;
import org.insurance.exception.InsuranceException;
import org.insurance.out.ZipCodeOut;
import org.insurance.service.check.IContactCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IZipCodeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ZipCodeService implements IZipCodeService {

	static final Logger logger = Logger.getLogger(ZipCodeService.class);

	@Inject
	private IZipCodeInfo zipCodeInfo;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IContactCheck contactCheck;

	@Override
	public ZipCodeOut getZipCodesByZipCode(String userId, String zipCode, String country) throws InsuranceException {
		userCheck.checkUser(userId);
		if (country == null) {
			Cod_country codCountry = contactCheck.checkAndGetDefaultCountry();
			country = codCountry.getCcountry();
		}
		return populateZipCodeOut(zipCodeInfo.getZipCodesByZipCode(zipCode, country));
	}

	@Override
	public ZipCodeOut getZipCodesByCity(String userId, String city, String country) throws InsuranceException {
		userCheck.checkUser(userId);
		return populateZipCodeOut(zipCodeInfo.getZipCodesByCity(city, country));
	}

	private ZipCodeOut populateZipCodeOut(Cod_postal cPostal) {
		ZipCodeOut result = new ZipCodeOut();
		if (cPostal != null) {
			result.setCity(cPostal.getCity());
			result.setCountry(cPostal.getCcountry());
			result.setZipCode(cPostal.getCpostal());
		}
		return result;
	}
}
