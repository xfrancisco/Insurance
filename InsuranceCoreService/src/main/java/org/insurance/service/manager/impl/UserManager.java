package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.conf.Usr_user;
import org.insurance.exception.UserException;
import org.insurance.exception.UserException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IUserInfo;
import org.insurance.service.manager.IUserManager;
import org.insurance.service.transactional.IUserOperation;
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
		userCheck.checkUser(usrUser.getCuser());
		userCheck.checkRole(usrUser.getCrole());
		if (userInfo.hasUserChanged(usrUser))
			userOperation.updateUser(cuser, usrUser);

	}

}