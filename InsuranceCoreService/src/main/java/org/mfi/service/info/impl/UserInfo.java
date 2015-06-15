package org.mfi.service.info.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mfi.conf.Usr_role;
import org.mfi.conf.Usr_user;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IUserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfo extends ServiceCore implements IUserInfo {

	@Override
	public Usr_user getUser(String cuser) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_user.class);
		criteria.add(Restrictions.eq("cuser", cuser));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Usr_role getRole(String crole) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_role.class);
		criteria.add(Restrictions.eq("crole", crole));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Usr_user> getUsers() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usr_user.class);
		return genericDao.getByCriteria(criteria);

	}

}
