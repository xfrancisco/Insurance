package org.mfi.service.transactional.impl;

import org.mfi.conf.Usr_user;
import org.mfi.service.ServiceCore;
import org.mfi.service.transactional.IUserOperation;
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
	public void updateUser(final String cuser, Usr_user usrUser, final Usr_user oldUsrUser) {
		usrUser.setCreationDate(oldUsrUser.getCreationDate());
		usrUser.setCusercre(oldUsrUser.getCusercre());
		usrUser.setModifDate(dbHelper.getNow());
		usrUser.setCusermod(cuser);
		genericDao.merge(usrUser);

	}

}
