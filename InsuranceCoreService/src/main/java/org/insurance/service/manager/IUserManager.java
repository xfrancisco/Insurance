package org.insurance.service.manager;

import org.insurance.conf.Usr_user;
import org.insurance.exception.UserException;

public interface IUserManager {

	void insertUser(String cuser, Usr_user usrUser) throws UserException;

	void updateUser(String cuser, Usr_user usrUser) throws UserException;
}
