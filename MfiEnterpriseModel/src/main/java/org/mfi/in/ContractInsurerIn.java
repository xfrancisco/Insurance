package org.mfi.in;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Percentage;

public class ContractInsurerIn {

	@Percentage
	private String insurerShare;

	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long insurerId;

	public String getInsurerShare() {
		return insurerShare;
	}

	public void setInsurerShare(String insurerShare) {
		this.insurerShare = insurerShare;
	}

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
	}

}
