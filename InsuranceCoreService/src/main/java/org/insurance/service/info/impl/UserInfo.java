package org.insurance.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Usr_role;
import org.insurance.conf.Usr_user;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IUserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfo extends ServiceCore implements IUserInfo {

	@Override
	public Usr_user getUser(String cuser) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_user.class, cuser);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Usr_role getRole(String crole) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_role.class, crole);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Usr_user> getUsers() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_user.class);
		return genericDao.getByCriteria(criteria);

	}

}
