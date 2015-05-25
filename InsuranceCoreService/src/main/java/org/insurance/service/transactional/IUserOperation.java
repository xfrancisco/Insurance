package org.insurance.service.transactional;

import org.insurance.conf.Usr_user;

public interface IUserOperation {

	void insertUser(String cuser, Usr_user usrUser);

	void updateUser(String cuser, Usr_user usrUser);

}
