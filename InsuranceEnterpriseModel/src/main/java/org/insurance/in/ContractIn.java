package org.insurance.in;

import java.util.List;

import javax.validation.Valid;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

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
	private int quoteId;

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
	@Length(max = EnterpriseModelEnum.USERID)
	private String underwriterId;

	@Mandatory
	private String startDate;

	private String endDate;

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

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
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

}