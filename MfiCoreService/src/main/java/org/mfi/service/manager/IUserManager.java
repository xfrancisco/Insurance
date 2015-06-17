package org.mfi.service.manager;

import org.mfi.conf.Usr_user;
import org.mfi.exception.UserException;

public interface IUserManager {

	void insertUser(String cuser, Usr_user usrUser) throws UserException;

	void updateUser(String cuser, Usr_user usrUser) throws UserException;
}
