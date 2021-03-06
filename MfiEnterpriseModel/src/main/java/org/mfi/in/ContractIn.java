package org.mfi.in;

import java.util.List;

import javax.validation.Valid;

import org.mfi.validation.constraints.BigDecimalMin;
import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;
import org.mfi.validation.constraints.Percentage;

import com.google.common.base.Strings;

public class ContractIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long personId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long leaderId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long brokerId;

	@Length(max = EnterpriseModelEnum.QUOTEID)
	private Integer quoteId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.BRANCH)
	private String branchId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.CATEGORY)
	private String categoryId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.DURATION)
	private String durationId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.FREQUENCY)
	private String frequencyId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.USERID)
	private String underwriterId;

	@Mandatory
	private String startDate;

	private String endDate;

	private String renewalDate;

	@Mandatory
	@Percentage
	private String leaderShare;

	@Mandatory
	@Percentage
	private String leadingCommissionRate;

	@Mandatory
	@Percentage
	private String brokerCommissionRate;

	@Mandatory
	@BigDecimalMin
	private String policyFees;

	@Valid
	private List<ContractInsurerIn> coinsurers;

	@Valid
	private List<GuaranteeIn> guarantees;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public Integer getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public List<GuaranteeIn> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<GuaranteeIn> guarantees) {
		this.guarantees = guarantees;
	}

	public String getBranchId() {
		return Strings.nullToEmpty(branchId).toUpperCase().trim();
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCategoryId() {
		return Strings.nullToEmpty(categoryId).toUpperCase().trim();
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDurationId() {
		return Strings.nullToEmpty(durationId).toUpperCase().trim();
	}

	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}

	public String getUnderwriterId() {
		return Strings.nullToEmpty(underwriterId).toUpperCase().trim();
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

	public String getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
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

	public List<ContractInsurerIn> getCoinsurers() {
		return coinsurers;
	}

	public void setCoinsurers(List<ContractInsurerIn> coinsurers) {
		this.coinsurers = coinsurers;
	}

}