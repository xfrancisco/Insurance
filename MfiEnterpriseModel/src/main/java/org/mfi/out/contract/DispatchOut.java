package org.mfi.out.contract;

import org.mfi.annotations.NameSetter;

public class DispatchOut {

	@NameSetter
	private Long insurerId;

	private String insurerIdLabel;

	private String insurerShare;

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

	public String getInsurerIdLabel() {
		return insurerIdLabel;
	}

	public void setInsurerIdLabel(String insurerIdLabel) {
		this.insurerIdLabel = insurerIdLabel;
	}

}
