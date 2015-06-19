package org.mfi.dto.contract;

import java.math.BigDecimal;

public class InsurerDispatchDto {

	private long numcliinsurer;
	private BigDecimal insurerShare;

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

}
