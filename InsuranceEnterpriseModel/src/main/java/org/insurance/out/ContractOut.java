package org.insurance.out;

import java.util.List;

import com.google.common.base.Strings;

public class ContractOut {

	private Long personId;

	private Long leaderId;

	private Long brokerId;

	private Integer quoteId;

	private Integer contractId;

	private String branchId;

	private String categoryId;

	private String durationId;

	private String frequencyId;

	private String underwriterId;

	private String startDate;

	private String endDate;

	private List<GuaranteeOut> guarantees;

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

	public List<GuaranteeOut> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<GuaranteeOut> guarantees) {
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

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

}