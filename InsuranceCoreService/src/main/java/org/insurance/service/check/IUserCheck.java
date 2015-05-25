package org.insurance.service.check;

import org.insurance.exception.UserException;

public interface IUserCheck {
	void checkUser(String cuser) throws UserException;

	void checkRole(String crole) throws UserException;
}
