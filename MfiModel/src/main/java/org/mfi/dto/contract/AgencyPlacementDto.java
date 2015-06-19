package org.mfi.dto.contract;

import java.math.BigDecimal;

public class AgencyPlacementDto {

	private long numcliinsurer;
	private BigDecimal insurerShare;
	private BigDecimal agencyCommissionRate;

	public long getNumcliinsurer() {
		return numcliinsurer;
	}

	public void setNumcliinsurer(long numcliinsurer) {
		this.numcliinsurer = numcliinsurer;
	}

	public BigDecimal getInsurerShare() {
		return insurerShare;
	}

	public void setInsurerShare(BigDecimal insurerShare) {
		this.insurerShare = insurerShare;
	}

	public BigDecimal getAgencyCommissionRate() {
		return agencyCommissionRate;
	}

	public void setAgencyCommissionRate(BigDecimal agencyCommissionRate) {
		this.agencyCommissionRate = agencyCommissionRate;
	}

}
