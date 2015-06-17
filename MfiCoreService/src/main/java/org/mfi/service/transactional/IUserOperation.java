package org.mfi.service.transactional;

import org.mfi.conf.Usr_user;

public interface IUserOperation {

	void insertUser(String cuser, Usr_user usrUser);

	void updateUser(String cuser, Usr_user usrUser);

}
