package org.insurance.dto.contract;

import java.math.BigDecimal;
import java.util.List;

public class GuaranteeDto {

	private String csection;
	private String cguarantee;
	private String cpremium;

	private BigDecimal guaranteedAmount;
	private BigDecimal premiumAmount;
	private BigDecimal brokerRate;

	private List<DispatchDto> dispatch;

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public String getCguarantee() {
		return cguarantee;
	}

	public void setCguarantee(String cguarantee) {
		this.cguarantee = cguarantee;
	}

	public String getCpremium() {
		return cpremium;
	}

	public void setCpremium(String cpremium) {
		this.cpremium = cpremium;
	}

	public BigDecimal getGuaranteedAmount() {
		return guaranteedAmount;
	}

	public void setGuaranteedAmount(BigDecimal guaranteedAmount) {
		this.guaranteedAmount = guaranteedAmount;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public List<DispatchDto> getDispatch() {
		return dispatch;
	}

	public void setDispatch(List<DispatchDto> dispatch) {
		this.dispatch = dispatch;
	}

	public BigDecimal getBrokerRate() {
		return brokerRate;
	}

	public void setBrokerRate(BigDecimal brokerRate) {
		this.brokerRate = brokerRate;
	}
}
