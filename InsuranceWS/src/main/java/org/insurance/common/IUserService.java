package org.insurance.common;

import java.util.List;

import org.insurance.exception.UserException;
import org.insurance.in.UserIn;
import org.insurance.out.UserOut;

public interface IUserService {

	UserOut insertUser(String userId, UserIn userIn) throws UserException;

	UserOut updateUser(String userId, UserIn userIn) throws UserException;

	List<UserOut> getUsers(String userId) throws UserException;

}
