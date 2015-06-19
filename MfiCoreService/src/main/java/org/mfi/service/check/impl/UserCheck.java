package org.mfi.service.check.impl;

import javax.inject.Inject;

import org.mfi.conf.Usr_role;
import org.mfi.conf.Usr_user;
import org.mfi.exception.UserException;
import org.mfi.exception.UserException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IUserInfo;

public class UserCheck extends ServiceCore implements IUserCheck {

	@Inject
	private IUserInfo userInfo;

	@Override
	public Usr_user checkUser(final String cuser) throws UserException {
		Usr_user usrUser = userInfo.getUser(cuser);
		if (usrUser == null) {
			throw new UserException(ErrorCode.ERR_BIZ_USER_UNKNOWN_USER, cuser);
		}
		return usrUser;
	}

	@Override
	public Usr_role checkRole(final String crole) throws UserException {
		Usr_role usrRole = userInfo.getRole(crole);
		if (usrRole == null) {
			throw new UserException(ErrorCode.ERR_BIZ_USER_UNKNOWN_ROLE, crole);
		}
		return usrRole;

	}

	@Override
	public Usr_user checkUserWithoutValidity(String cuser) throws UserException {
		Usr_user usrUser = userInfo.getUserWithoutValidity(cuser);
		if (usrUser == null) {
			throw new UserException(ErrorCode.ERR_BIZ_USER_UNKNOWN_USER, cuser);
		}
		return usrUser;
	}
}
