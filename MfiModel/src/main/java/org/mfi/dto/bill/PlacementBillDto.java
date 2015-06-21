package org.mfi.dto.bill;

import java.math.BigDecimal;

public class PlacementBillDto {

	private long numcliins;
	private BigDecimal placementAmount;
	private BigDecimal agencyAmount;

	public PlacementBillDto(BigDecimal placementAmount, BigDecimal agencyAmount, long numcliins) {
		this.placementAmount = placementAmount;
		this.agencyAmount = agencyAmount;
		this.numcliins = numcliins;
	}

	public long getNumcliins() {
		return numcliins;
	}

	public void setNumcliins(long numcliins) {
		this.numcliins = numcliins;
	}

	public BigDecimal getPlacementAmount() {
		return placementAmount;
	}

	public void setPlacementAmount(BigDecimal placementAmount) {
		this.placementAmount = placementAmount;
	}

	public BigDecimal getAgencyAmount() {
		return agencyAmount;
	}

	public void setAgencyAmount(BigDecimal agencyAmount) {
		this.agencyAmount = agencyAmount;
	}

}
