package org.insurance.service.info.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_postal;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IZipCodeInfo;
import org.springframework.stereotype.Service;

@Service
public class ZipCodeInfo extends ServiceCore implements IZipCodeInfo {

	@Override
	public Cod_postal getZipCodesByZipCode(String cpostal, String ccountry) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_postal.class);
		criteria.add(Restrictions.eq("cpostal", cpostal));
		criteria.add(Restrictions.eq("ccountry", ccountry));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_postal getZipCodesByCity(String city, String ccountry) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_postal.class);
		criteria.add(Restrictions.eq("city", city));
		criteria.add(Restrictions.eq("ccountry", ccountry));
		return genericDao.getFirstByCriteria(criteria);
	}

	
}
