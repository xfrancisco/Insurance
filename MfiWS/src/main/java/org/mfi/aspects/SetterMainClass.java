package org.mfi.aspects;

import org.mfi.service.info.IPersonInfo;

public class SetterMainClass {
	private IPersonInfo personInfo;

	public IPersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(IPersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public String getPersonLabel(Long numcli) {
		return personInfo.getPersonLabel(numcli);
	}

}
