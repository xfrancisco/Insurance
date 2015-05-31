package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_address;
import org.insurance.conf.Cod_country;
import org.insurance.conf.Cod_email;
import org.insurance.conf.Cod_phone;
import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IContactInfo;
import org.insurance.util.MappingUtils;
import org.springframework.stereotype.Service;

@Service
public class ContactInfo extends ServiceCore implements IContactInfo {

	@Override
	public Cli_address getAddressByType(long numcli, String caddress) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("caddress", caddress));
		criteria.add(Restrictions.le("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("endval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_phone> getPhones(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_phone.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.le("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("endval"));
		List<Cli_phone> result = genericDao.getByCriteria(criteria);
		for (Cli_phone cliPhone : result) {
			Cod_phone codPhone = genericDao.get(Cod_phone.class, cliPhone.getCphone());
			cliPhone.setMobile(MappingUtils.toBoolean(codPhone.getIndmobile()));
		}
		return result;
	}

	@Override
	public List<Cli_email> getEmails(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_email.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.le("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("endval"));
		return genericDao.getByCriteria(criteria);
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
		criteria.add(Restrictions.le("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("endval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cli_phone getPhoneByType(long numcli, String cemail) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_phone.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("cphone", cemail));
		criteria.add(Restrictions.le("startval", dbHelper.getToday()));
		criteria.add(Restrictions.isNull("endval"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_address> getOldAddresses(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_address.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.isNotNull("startval"));
		criteria.add(Restrictions.isNotNull("endval"));
		criteria.addOrder(Order.desc("startval"));
		criteria.addOrder(Order.desc("numaddress"));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cli_phone> getOldPhones(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_phone.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.isNotNull("startval"));
		criteria.add(Restrictions.isNotNull("endval"));
		criteria.addOrder(Order.desc("startval"));
		criteria.addOrder(Order.desc("numphone"));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cli_email> getOldEmails(long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_email.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.isNotNull("startval"));
		criteria.add(Restrictions.isNotNull("endval"));
		criteria.addOrder(Order.desc("startval"));
		criteria.addOrder(Order.desc("numemail"));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cod_address getDefaultAddressType() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_address.class);
		criteria.add(Restrictions.eq("inddefault", "1"));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_phone getPhoneType(String cphone) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_phone.class);
		criteria.add(Restrictions.eq("cphone", cphone));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_email getEmailType(String cemail) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_email.class);
		criteria.add(Restrictions.eq("cemail", cemail));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_country getDefaultCountry() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_country.class);
		criteria.add(Restrictions.eq("inddefault", "1"));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cod_postal> getZipCodesByZipCode(String cpostal, String ccountry) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_postal.class);
		criteria.add(Restrictions.ilike("cpostal", cpostal, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("ccountry", ccountry));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public List<Cod_postal> getZipCodesByCity(String city, String ccountry) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_postal.class);
		criteria.add(Restrictions.ilike("city", city, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("ccountry", ccountry));
		return genericDao.getByCriteria(criteria);
	}
}
