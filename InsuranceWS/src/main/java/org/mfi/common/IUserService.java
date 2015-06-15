package org.mfi.common;

import java.util.List;

import org.mfi.exception.UserException;
import org.mfi.in.UserIn;
import org.mfi.out.UserOut;

public interface IUserService {

	UserOut insertUser(String userId, UserIn userIn) throws UserException;

	UserOut updateUser(String userId, UserIn userIn) throws UserException;

	List<UserOut> getUsers(String userId) throws UserException;

}
