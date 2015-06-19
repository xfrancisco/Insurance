package org.mfi.service.manager.impl;

import javax.inject.Inject;

import org.mfi.conf.Usr_user;
import org.mfi.exception.UserException;
import org.mfi.exception.UserException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IUserInfo;
import org.mfi.service.manager.IUserManager;
import org.mfi.service.transactional.IUserOperation;
import org.springframework.stereotype.Service;

@Service
public class UserManager extends ServiceCore implements IUserManager {
	@Inject
	private IUserCheck userCheck;

	@Inject
	private IUserInfo userInfo;

	@Inject
	private IUserOperation userOperation;

	@Override
	public void insertUser(String cuser, Usr_user usrUser) throws UserException {
		userCheck.checkRole(usrUser.getCrole());
		Usr_user user = userInfo.getUser(usrUser.getCuser());
		if (user != null)
			throw new UserException(ErrorCode.ERR_BIZ_USER_ALREADY_EXISTS, usrUser.getCuser());
		userOperation.insertUser(cuser, usrUser);
	}

	@Override
	public void updateUser(String cuser, Usr_user usrUser) throws UserException {
		Usr_user oldUsrUser = userCheck.checkUserWithoutValidity(usrUser.getCuser());
		userCheck.checkRole(usrUser.getCrole());
		if (!oldUsrUser.equals(usrUser))
			userOperation.updateUser(cuser, usrUser, oldUsrUser);

	}

}