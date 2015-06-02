package org.insurance.service.check;

import org.insurance.conf.Usr_role;
import org.insurance.conf.Usr_user;
import org.insurance.exception.UserException;

public interface IUserCheck {
	Usr_user checkUser(String cuser) throws UserException;

	Usr_role checkRole(String crole) throws UserException;
}
