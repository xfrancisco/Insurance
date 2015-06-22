package org.mfi.out.billing;

import org.mfi.annotations.NameSetter;

public class InsurerBillOut {

	@NameSetter
	private long insurerId;
	private String insurerIdLabel;

	private String amount;

	public long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(long insurerId) {
		this.insurerId = insurerId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInsurerIdLabel() {
		return insurerIdLabel;
	}

	public void setInsurerIdLabel(String insurerIdLabel) {
		this.insurerIdLabel = insurerIdLabel;
	}

}
