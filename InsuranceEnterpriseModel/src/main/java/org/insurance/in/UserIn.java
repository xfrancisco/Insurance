package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

import com.google.common.base.Strings;

public class UserIn {
	@Mandatory
	@Length(max = EnterpriseModelEnum.USERID)
	private String userId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.USERNAME)
	private String userName;

	@Mandatory
	@Length(max = EnterpriseModelEnum.USERPROFILE)
	private String profile;

	public String getUserId() {
		return Strings.nullToEmpty(userId).toUpperCase();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return Strings.nullToEmpty(userName).toUpperCase();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfile() {
		return Strings.nullToEmpty(profile).toUpperCase();
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
