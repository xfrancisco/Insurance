package org.insurance.dto.contract;

import java.math.BigDecimal;

public class DispatchDto {

	private long numcliinsurer;
	private Long numclibroker;
	private BigDecimal brokerShare;
	private BigDecimal insurerShare;

	public long getNumcliinsurer() {
		return numcliinsurer;
	}

	public void setNumcliinsurer(long numcliinsurer) {
		this.numcliinsurer = numcliinsurer;
	}

	public Long getNumclibroker() {
		return numclibroker;
	}

	public void setNumclibroker(Long numclibroker) {
		this.numclibroker = numclibroker;
	}

	public BigDecimal getBrokerShare() {
		return brokerShare;
	}

	public void setBrokerShare(BigDecimal brokerShare) {
		this.brokerShare = brokerShare;
	}

	public BigDecimal getInsurerShare() {
		return insurerShare;
	}

	public void setInsurerShare(BigDecimal insurerShare) {
		this.insurerShare = insurerShare;
	}

}
