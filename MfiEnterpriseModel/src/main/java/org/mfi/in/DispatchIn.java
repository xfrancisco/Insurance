package org.mfi.in;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Percentage;

public class DispatchIn {

	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long insurerId;

	@Percentage
	private String insurerShare;

	@Percentage
	private String insurerCommissionRate;

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerShare() {
		return insurerShare;
	}

	public void setInsurerShare(String insurerShare) {
		this.insurerShare = insurerShare;
	}

	public String getInsurerCommissionRate() {
		return insurerCommissionRate;
	}

	public void setInsurerCommissionRate(String insurerCommissionRate) {
		this.insurerCommissionRate = insurerCommissionRate;
	}

}
