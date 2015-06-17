package org.mfi.service.check;

import org.mfi.conf.Usr_role;
import org.mfi.conf.Usr_user;
import org.mfi.exception.UserException;

public interface IUserCheck {
	Usr_user checkUser(final String cuser) throws UserException;

	Usr_role checkRole(final String crole) throws UserException;
}
