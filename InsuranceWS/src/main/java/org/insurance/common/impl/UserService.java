package org.insurance.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IUserService;
import org.insurance.conf.Usr_user;
import org.insurance.exception.UserException;
import org.insurance.in.UserIn;
import org.insurance.out.UserOut;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IUserInfo;
import org.insurance.service.manager.IUserManager;
import org.insurance.utils.mapping.UserMapping;
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
