package org.insurance.service.transactional.impl;

import org.insurance.conf.Usr_user;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IUserOperation;
import org.springframework.stereotype.Service;

@Service
public class UserOperation extends ServiceCore implements IUserOperation {

	@Override
	public void insertUser(final String cuser, Usr_user usrUser) {
		usrUser.setCreationDate(dbHelper.getNow());
		usrUser.setCusercre(cuser);
		genericDao.save(usrUser);

	}

	@Override
	public void updateUser(final String cuser, Usr_user usrUser) {
		usrUser.setModifDate(dbHelper.getNow());
		usrUser.setCusermod(cuser);
		genericDao.saveOrUpdate(usrUser);

	}

}
