package org.insurance.dto.contract;

import java.math.BigDecimal;

public class DispatchDto {

	private long numcliinsurer;
	private BigDecimal brokerRate;
	private BigDecimal insurerShare;
	private BigDecimal insurerRate;

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

	public BigDecimal getInsurerRate() {
		return insurerRate;
	}

	public void setInsurerRate(BigDecimal insurerRate) {
		this.insurerRate = insurerRate;
	}

	public BigDecimal getBrokerRate() {
		return brokerRate;
	}

	public void setBrokerRate(BigDecimal brokerRate) {
		this.brokerRate = brokerRate;
	}

}
