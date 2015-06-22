package org.mfi.out.billing;

import org.mfi.annotations.NameSetter;

public class LeadingFeesBillOut {
	@NameSetter
	private long insurerId;
	private String insurerIdLabel;
	private String rate;
	private String amount;
	private String share;

	public long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(long insurerId) {
		this.insurerId = insurerId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getInsurerIdLabel() {
		return insurerIdLabel;
	}

	public void setInsurerIdLabel(String insurerIdLabel) {
		this.insurerIdLabel = insurerIdLabel;
	}

}
