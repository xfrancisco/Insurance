package org.mfi.in;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;

public class UpdatePersonIn extends InsertPersonIn {
	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private long personId;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

}
