package org.mfi.out.contract;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Percentage;

public class DispatchOut {

	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long insurerId;

	@Percentage
	private String insurerShare;

	@Percentage
	private String agencyCommissionRate;

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

	public String getAgencyCommissionRate() {
		return agencyCommissionRate;
	}

	public void setAgencyCommissionRate(String agencyCommissionRate) {
		this.agencyCommissionRate = agencyCommissionRate;
	}

}
