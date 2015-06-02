package org.insurance.in;

import org.insurance.validation.constraints.BigDecimalMin;
import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;
import org.insurance.validation.constraints.Percentage;

public class NewQuoteIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private long personId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private long brokerId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private long leaderId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.BRANCH)
	private String branchId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.CATEGORY)
	private String categoryId;

	@Mandatory
	private String workingDate;

	@Mandatory
	private String receptionDate;

	@Mandatory
	@Length(max = EnterpriseModelEnum.USERID)
	private String underwriterId;

	@Mandatory
	@Length(max = EnterpriseModelEnum.QUOTESTATUS)
	private String quoteStatusId;

	@Length(max = EnterpriseModelEnum.DURATION)
	private String durationId;

	@BigDecimalMin
	private String guaranteedAmount;

	@BigDecimalMin
	private String premiumAmount;

	private String validityEndDate;

	private String acceptanceDate;

	@Percentage
	private String share;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}

	public long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(long leaderId) {
		this.leaderId = leaderId;
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

	public String getWorkingDate() {
		return workingDate;
	}

	public void setWorkingDate(String workingDate) {
		this.workingDate = workingDate;
	}

	public String getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}

	public String getUnderwriterId() {
		return underwriterId;
	}

	public void setUnderwriterId(String underwriterId) {
		this.underwriterId = underwriterId;
	}

	public String getQuoteStatusId() {
		return quoteStatusId;
	}

	public void setQuoteStatusId(String quoteStatusId) {
		this.quoteStatusId = quoteStatusId;
	}

	public String getDurationId() {
		return durationId;
	}

	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}

	public String getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(String validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getGuaranteedAmount() {
		return guaranteedAmount;
	}

	public void setGuaranteedAmount(String guaranteedAmount) {
		this.guaranteedAmount = guaranteedAmount;
	}

	public String getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

}
