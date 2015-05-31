package org.insurance.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IZipCodeService;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_postal;
import org.insurance.exception.ContactException;
import org.insurance.exception.ContactException.ErrorCode;
import org.insurance.exception.InsuranceException;
import org.insurance.in.ZipCodeIn;
import org.insurance.out.ZipCodeOut;
import org.insurance.service.check.IContactCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IContactInfo;
import org.insurance.service.transactional.IContactOperation;
import org.insurance.util.MappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ZipCodeService implements IZipCodeService {

	static final Logger logger = Logger.getLogger(ZipCodeService.class);

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IContactCheck contactCheck;

	@Inject
	private IContactInfo contactInfo;

	@Inject
	private IContactOperation contactOperation;

	@Override
	public List<ZipCodeOut> getZipCodesByZipCode(String userId, String zipCode, String country) throws InsuranceException {
		userCheck.checkUser(userId);
		if (country == null) {
			Cod_country codCountry = contactCheck.checkAndGetDefaultCountry();
			country = codCountry.getCcountry();
		}
		return populateZipCodeOut(contactInfo.getZipCodesByZipCode(zipCode, country));
	}

	@Override
	public List<ZipCodeOut> getZipCodesByCity(String userId, String city, String country) throws InsuranceException {
		userCheck.checkUser(userId);
		if (country == null) {
			Cod_country codCountry = contactCheck.checkAndGetDefaultCountry();
			country = codCountry.getCcountry();
		}
		return populateZipCodeOut(contactInfo.getZipCodesByCity(city, country));
	}

	private ZipCodeOut populateZipCodeOut(Cod_postal cPostal) {
		ZipCodeOut result = new ZipCodeOut();
		if (cPostal != null) {
			result.setCity(cPostal.getCity());
			result.setCountry(cPostal.getCcountry());
			result.setZipCode(cPostal.getCpostal());
			result.setValid(MappingUtils.toBoolean(cPostal.getIndvali()));
		}
		return result;
	}

	private List<ZipCodeOut> populateZipCodeOut(List<Cod_postal> zipCodes) {
		List<ZipCodeOut> result = new ArrayList<ZipCodeOut>();
		for (Cod_postal cPostal : zipCodes) {
			result.add(populateZipCodeOut(cPostal));
		}
		return result;
	}

	@Override
	public ZipCodeOut createZipCode(String userId, ZipCodeIn zipCodeIn) throws InsuranceException {
		String ccountry = zipCodeIn.getCountry();
		String cpostal = zipCodeIn.getZipCode();
		String city = zipCodeIn.getCity();
		if (Strings.isNullOrEmpty(ccountry))
			ccountry = contactCheck.checkAndGetDefaultCountry().getCcountry();
		Cod_postal codPostal = contactInfo.getZipCode(cpostal, city, ccountry);
		if (codPostal != null) {
			throw new ContactException(ErrorCode.ERR_BIZ_CONTACT_ADDRESS_EXISTING_ZIPCODE);
		}

		codPostal = new Cod_postal(cpostal, city, ccountry);
		codPostal.setIndvali("1");
		contactOperation.createZipCode(codPostal);
		return populateZipCodeOut(codPostal);
	}
}
