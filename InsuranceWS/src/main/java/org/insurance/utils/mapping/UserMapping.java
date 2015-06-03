package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.conf.Usr_user;
import org.insurance.in.UserIn;
import org.insurance.out.UserOut;
import org.insurance.util.MappingUtils;

public final class UserMapping {

	public static Usr_user populateUser(UserIn userIn) {
		Usr_user result = new Usr_user();
		if (userIn != null) {
			result.setCuser(userIn.getUserId());
			result.setCrole(userIn.getProfile());
			result.setLuser(userIn.getUserName());
			result.setIndvali(MappingUtils.boolToString(userIn.getIsValid()));
		}
		return result;
	}

	public static UserOut populateUser(Usr_user usrUser) {
		UserOut result = new UserOut();
		if (usrUser != null) {
			result.setProfile(usrUser.getCrole());
			result.setUserId(usrUser.getCuser());
			result.setUserName(usrUser.getLuser());
			result.setIsValid(MappingUtils.toBoolean(usrUser.getIndvali()));
		}
		return result;
	}

	public static List<UserOut> populateUsers(List<Usr_user> usrUsers) {
		List<UserOut> result = new ArrayList<UserOut>();
		for (Usr_user usrUser : usrUsers) {
			result.add(populateUser(usrUser));
		}
		return result;
	}

}
