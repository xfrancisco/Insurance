package org.mfi.dto.bill;

import java.math.BigDecimal;
import java.util.Map;

import org.mfi.conf.Cod_tax;

public class PremiumBillDto {

	private String cpremium;
	private String cguarantee;
	private String csection;
	private Cod_tax codtax;
	private BigDecimal netPremiumAmount;
	private BigDecimal taxAmount;
	private BigDecimal grossPremiumAmount;
	private BigDecimal brokerRate;
	private BigDecimal brokerAmount;
	private BigDecimal netCompanyAmount;
	private BigDecimal policyFees;
	private Map<Long, CoinsurerBillDto> coinsurers;
	private Map<Long, PlacementBillDto> placements;
	private Map<Long, LeadingFeeDto> leadingFees;

	public String getCpremium() {
		return cpremium;
	}

	public void setCpremium(String cpremium) {
		this.cpremium = cpremium;
	}

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public Cod_tax getCodtax() {
		return codtax;
	}

	public void setCodtax(Cod_tax codtax) {
		this.codtax = codtax;
	}

	public BigDecimal getNetPremiumAmount() {
		return netPremiumAmount;
	}

	public void setNetPremiumAmount(BigDecimal netPremiumAmount) {
		this.netPremiumAmount = netPremiumAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getGrossPremiumAmount() {
		return grossPremiumAmount;
	}

	public void setGrossPremiumAmount(BigDecimal grossPremiumAmount) {
		this.grossPremiumAmount = grossPremiumAmount;
	}

	public BigDecimal getBrokerRate() {
		return brokerRate;
	}

	public void setBrokerRate(BigDecimal brokerRate) {
		this.brokerRate = brokerRate;
	}

	public BigDecimal getBrokerAmount() {
		return brokerAmount;
	}

	public void setBrokerAmount(BigDecimal brokerAmount) {
		this.brokerAmount = brokerAmount;
	}

	public BigDecimal getNetCompanyAmount() {
		return netCompanyAmount;
	}

	public void setNetCompanyAmount(BigDecimal netCompanyAmount) {
		this.netCompanyAmount = netCompanyAmount;
	}

	public String getCguarantee() {
		return cguarantee;
	}

	public void setCguarantee(String cguarantee) {
		this.cguarantee = cguarantee;
	}

	public BigDecimal getPolicyFees() {
		return policyFees;
	}

	public void setPolicyFees(BigDecimal policyFees) {
		this.policyFees = policyFees;
	}

	public Map<Long, CoinsurerBillDto> getCoinsurers() {
		return coinsurers;
	}

	public void setCoinsurers(Map<Long, CoinsurerBillDto> coinsurers) {
		this.coinsurers = coinsurers;
	}

	public Map<Long, PlacementBillDto> getPlacements() {
		return placements;
	}

	public void setPlacements(Map<Long, PlacementBillDto> placements) {
		this.placements = placements;
	}

	public Map<Long, LeadingFeeDto> getLeadingFees() {
		return leadingFees;
	}

	public void setLeadingFees(Map<Long, LeadingFeeDto> leadingFees) {
		this.leadingFees = leadingFees;
	}

}
