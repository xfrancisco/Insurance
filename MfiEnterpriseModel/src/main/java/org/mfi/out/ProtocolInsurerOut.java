package org.mfi.out;

import org.mfi.annotations.NameSetter;

public class ProtocolInsurerOut {

	@NameSetter
	private long insurerId;
	private String insurerIdLabel;

	public String getInsurerIdLabel() {
		return insurerIdLabel;
	}

	public void setInsurerIdLabel(String insurerIdLabel) {
		this.insurerIdLabel = insurerIdLabel;
	}

	public void setInsurerId(long insurerId) {
		this.insurerId = insurerId;
	}

	public long getInsurerId() {
		return insurerId;
	}

}
