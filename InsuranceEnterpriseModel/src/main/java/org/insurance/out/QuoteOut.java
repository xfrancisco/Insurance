package org.insurance.out;

import java.math.BigDecimal;

public class QuoteOut {

	private long personId;

	private long quoteId;

	private long brokerId;

	private long leaderId;

	private String branchId;

	private String categoryId;

	private String workingDate;

	private String receptionDate;

	private String underwriterId;

	private String quoteStatusId;

	private String durationId;

	private BigDecimal guaranteedAmount;

	private BigDecimal premiumAmount;

	private String validityEndDate;

	private String acceptanceDate;

	private String cancellationDate;

	private String cancellationUser;

	private BigDecimal share;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
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

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
	}

	public String getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getCancellationUser() {
		return cancellationUser;
	}

	public void setCancellationUser(String cancellationUser) {
		this.cancellationUser = cancellationUser;
	}

}