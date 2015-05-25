package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Usr_role;
import org.insurance.conf.Usr_user;
import org.insurance.exception.UserException;
import org.insurance.exception.UserException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IUserInfo;

public class UserCheck extends ServiceCore implements IUserCheck {

	@Inject
	private IUserInfo userInfo;

	@Override
	public void checkUser(String cuser) throws UserException {
		Usr_user usrUser = userInfo.getUser(cuser);
		if (usrUser == null) {
			throw new UserException(ErrorCode.ERR_BIZ_USER_UNKNOWN_USER, cuser);
		}
	}

	@Override
	public void checkRole(String crole) throws UserException {
		Usr_role usrRole = userInfo.getRole(crole);
		if (usrRole == null) {
			throw new UserException(ErrorCode.ERR_BIZ_USER_UNKNOWN_ROLE, crole);
		}

	}
}
