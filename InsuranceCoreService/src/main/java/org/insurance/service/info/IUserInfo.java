package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Usr_role;
import org.insurance.conf.Usr_user;

public interface IUserInfo {

	Usr_user getUser(String cuser);

	Usr_role getRole(String crole);

	List<Usr_user> getUsers();
}
