package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

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
