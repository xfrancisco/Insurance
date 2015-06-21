package org.mfi.out.billing;

public class PlacementBillOut {

	private long insurerId;
	private String placementAmount;
	private String agencyAmount;

	public long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(long insurerId) {
		this.insurerId = insurerId;
	}

	public String getPlacementAmount() {
		return placementAmount;
	}

	public void setPlacementAmount(String placementAmount) {
		this.placementAmount = placementAmount;
	}

	public String getAgencyAmount() {
		return agencyAmount;
	}

	public void setAgencyAmount(String agencyAmount) {
		this.agencyAmount = agencyAmount;
	}

}
