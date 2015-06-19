package org.mfi.service.info;

import java.util.List;

import org.mfi.conf.Usr_role;
import org.mfi.conf.Usr_user;

public interface IUserInfo {

	Usr_user getUser(String cuser);

	Usr_role getRole(String crole);

	List<Usr_user> getUsers();

	Usr_user getUserWithoutValidity(String cuser);

}
