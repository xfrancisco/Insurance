package org.mfi.out.contract;

import org.mfi.annotations.NameSetter;

public class ContractInsurerOut {

	private String insurerShare;

	@NameSetter
	private Long insurerId;

	private String insurerIdLabel;

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

	public String getInsurerIdLabel() {
		return insurerIdLabel;
	}

	public void setInsurerIdLabel(String insurerIdLabel) {
		this.insurerIdLabel = insurerIdLabel;
	}

}
