package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;

public class InsurerIn {

	@Length(max = EnterpriseModelEnum.PERSONID)
	private long insurerId;

	public long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(long insurerId) {
		this.insurerId = insurerId;
	}

}
