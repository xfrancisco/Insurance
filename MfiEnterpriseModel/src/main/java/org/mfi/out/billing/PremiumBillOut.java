package org.mfi.out.billing;

import java.math.BigDecimal;
import java.util.List;

public class PremiumBillOut {
	private String premiumId;
	private String guaranteeId;
	private String sectionId;
	private BigDecimal netPremiumAmount;
	private BigDecimal taxAmount;
	private BigDecimal grossPremiumAmount;
	private BigDecimal brokerRate;
	private BigDecimal brokerAmount;
	private BigDecimal netCompanyAmount;
	private BigDecimal taxRate;
	private List<PlacementBillOut> placements;
	private List<InsurerBillOut> coinsurers;
	private List<LeadingFeesBillOut> leadingfees;

	public String getPremiumId() {
		return premiumId;
	}

	public void setPremiumId(String premiumId) {
		this.premiumId = premiumId;
	}

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
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

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public List<PlacementBillOut> getPlacements() {
		return placements;
	}

	public void setPlacements(List<PlacementBillOut> placements) {
		this.placements = placements;
	}

	public List<InsurerBillOut> getCoinsurers() {
		return coinsurers;
	}

	public void setCoinsurers(List<InsurerBillOut> coinsurers) {
		this.coinsurers = coinsurers;
	}

	public List<LeadingFeesBillOut> getLeadingfees() {
		return leadingfees;
	}

	public void setLeadingfees(List<LeadingFeesBillOut> leadingfees) {
		this.leadingfees = leadingfees;
	}

}
