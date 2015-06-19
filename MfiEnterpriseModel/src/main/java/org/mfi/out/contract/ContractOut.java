package org.mfi.out.contract;

import java.util.List;

import org.mfi.out.codes.GuaranteeOut;

public class ContractOut {
	private Long personId;
	private Integer contractId;
	private Long leaderId;
	private Long brokerId;
	private Integer quoteId;
	private String branchId;
	private String categoryId;
	private String durationId;
	private String frequencyId;
	private String underwriterId;
	private String startDate;
	private String endDate;
	private String renewalDate;
	private String leaderShare;
	private String leadingCommissionRate;
	private String brokerCommissionRate;
	private String policyFees;
	private List<ContractInsurerOut> coinsurers;
	private List<GuaranteeOut> guarantees;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Integer getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDurationId() {
		return durationId;
	}

	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}

	public String getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getUnderwriterId() {
		return underwriterId;
	}

	public void setUnderwriterId(String underwriterId) {
		this.underwriterId = underwriterId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	public String getLeaderShare() {
		return leaderShare;
	}

	public void setLeaderShare(String leaderShare) {
		this.leaderShare = leaderShare;
	}

	public String getLeadingCommissionRate() {
		return leadingCommissionRate;
	}

	public void setLeadingCommissionRate(String leadingCommissionRate) {
		this.leadingCommissionRate = leadingCommissionRate;
	}

	public String getBrokerCommissionRate() {
		return brokerCommissionRate;
	}

	public void setBrokerCommissionRate(String brokerCommissionRate) {
		this.brokerCommissionRate = brokerCommissionRate;
	}

	public String getPolicyFees() {
		return policyFees;
	}

	public void setPolicyFees(String policyFees) {
		this.policyFees = policyFees;
	}

	public List<ContractInsurerOut> getCoinsurers() {
		return coinsurers;
	}

	public void setCoinsurers(List<ContractInsurerOut> coinsurers) {
		this.coinsurers = coinsurers;
	}

	public List<GuaranteeOut> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<GuaranteeOut> guarantees) {
		this.guarantees = guarantees;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

}
