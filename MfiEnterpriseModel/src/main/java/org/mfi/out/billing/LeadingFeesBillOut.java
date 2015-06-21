package org.mfi.out.billing;

public class LeadingFeesBillOut {
	private long insurerId;
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

}
