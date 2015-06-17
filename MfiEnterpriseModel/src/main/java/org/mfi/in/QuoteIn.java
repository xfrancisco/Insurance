package org.mfi.in;

import org.mfi.validation.constraints.BigDecimalMin;
import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;
import org.mfi.validation.constraints.Percentage;

import com.google.common.base.Strings;

public class QuoteIn {

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

	@Length(max = EnterpriseModelEnum.COMMENT)
	private String comment;

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

	public String getWorkingDate() {
		return Strings.nullToEmpty(workingDate).toUpperCase().trim();
	}

	public void setWorkingDate(String workingDate) {
		this.workingDate = workingDate;
	}

	public String getReceptionDate() {
		return Strings.nullToEmpty(receptionDate).toUpperCase().trim();
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}

	public String getUnderwriterId() {
		return Strings.nullToEmpty(underwriterId).toUpperCase().trim();
	}

	public void setUnderwriterId(String underwriterId) {
		this.underwriterId = underwriterId;
	}

	public String getQuoteStatusId() {
		return Strings.nullToEmpty(quoteStatusId).toUpperCase().trim();
	}

	public void setQuoteStatusId(String quoteStatusId) {
		this.quoteStatusId = quoteStatusId;
	}

	public String getDurationId() {
		return Strings.nullToEmpty(durationId).toUpperCase().trim();
	}

	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}

	public String getValidityEndDate() {
		return Strings.nullToEmpty(validityEndDate).toUpperCase().trim();
	}

	public void setValidityEndDate(String validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public String getAcceptanceDate() {
		return Strings.nullToEmpty(acceptanceDate).toUpperCase().trim();
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getGuaranteedAmount() {
		return Strings.nullToEmpty(guaranteedAmount).toUpperCase().trim();
	}

	public void setGuaranteedAmount(String guaranteedAmount) {
		this.guaranteedAmount = guaranteedAmount;
	}

	public String getPremiumAmount() {
		return Strings.nullToEmpty(premiumAmount).toUpperCase().trim();
	}

	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getShare() {
		return Strings.nullToEmpty(share).toUpperCase().trim();
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getComment() {
		return Strings.nullToEmpty(comment).toUpperCase().trim();
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
