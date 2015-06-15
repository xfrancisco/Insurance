package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.mfi.common.IUserService;
import org.mfi.conf.Usr_user;
import org.mfi.exception.UserException;
import org.mfi.in.UserIn;
import org.mfi.out.UserOut;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IUserInfo;
import org.mfi.service.manager.IUserManager;
import org.mfi.utils.mapping.UserMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class UserService implements IUserService {

	static final Logger logger = Logger.getLogger(UserService.class);

	@Inject
	private IUserInfo userInfo;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IUserManager userManager;

	@Override
	public UserOut insertUser(String userId, UserIn userIn) throws UserException {
		userCheck.checkUser(userId);
		Usr_user usrUser = UserMapping.populateUser(userIn);
		userManager.insertUser(userId, usrUser);
		return UserMapping.populateUser(usrUser);
	}

	@Override
	public UserOut updateUser(String userId, UserIn userIn) throws UserException {
		userCheck.checkUser(userId);
		Usr_user usrUser = UserMapping.populateUser(userIn);
		userManager.updateUser(userId, usrUser);
		return UserMapping.populateUser(usrUser);
	}

	@Override
	public List<UserOut> getUsers(String userId) throws UserException {
		userCheck.checkUser(userId);
		return UserMapping.populateUsers(userInfo.getUsers());
	}

}
