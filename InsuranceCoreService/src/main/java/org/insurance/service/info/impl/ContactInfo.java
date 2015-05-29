package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IContactInfo;
import org.springframework.stereotype.Service;

@Service
public class ContactInfo extends ServiceCore implements IContactInfo {

	@Override
	public Cli_address getAddress(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.lt("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("finval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_phone> getPhones(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.lt("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("finval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_email> getEmails(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.lt("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("finval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_postal getZipCode(String cpostal, String city, String ccountry) {
		Cod_postal codPostal = new Cod_postal(cpostal, city, ccountry);
		return genericDao.get(Cod_postal.class, codPostal);
	}

	@Override
	public Cod_phone getDefaultPhoneType() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_phone.class);
		criteria.add(Restrictions.eq("indmobile", "0"));
		criteria.add(Restrictions.eq("inddefault", "1"));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_phone getDefaultMobilePhoneType() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_phone.class);
		criteria.add(Restrictions.eq("indmobile", "1"));
		criteria.add(Restrictions.eq("inddefault", "1"));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_email getDefaultEmailType() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_email.class);
		criteria.add(Restrictions.eq("inddefault", "1"));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cli_email getEmailByType(long numcli, String cemail) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_email.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("cemail", cemail));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cli_phone getPhoneByType(long numcli, String cemail) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_phone.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("cphone", cemail));
		return genericDao.getFirstByCriteria(criteria);
	}

}
