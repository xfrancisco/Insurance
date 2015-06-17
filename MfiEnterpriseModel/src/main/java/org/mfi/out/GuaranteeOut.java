package org.mfi.out;

import java.util.List;

import com.google.common.base.Strings;

public class GuaranteeOut {

	private String branchId;
	private String categoryId;
	private String sectionId;
	private String guaranteeId;
	private String premiumId;

	private String guaranteedAmount;

	private String premiumAmount;

	private String brokerCommissionRate;

	private String leaderShare;

	private List<DispatchOut> dispatch;

	public String getGuaranteeId() {
		return Strings.nullToEmpty(guaranteeId).toUpperCase().trim();
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getPremiumId() {
		return Strings.nullToEmpty(premiumId).toUpperCase().trim();
	}

	public void setPremiumId(String premiumId) {
		this.premiumId = premiumId;
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

	public List<DispatchOut> getDispatch() {
		return dispatch;
	}

	public void setDispatch(List<DispatchOut> dispatch) {
		this.dispatch = dispatch;
	}

	public String getSectionId() {
		return Strings.nullToEmpty(sectionId).toUpperCase().trim();
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getBrokerCommissionRate() {
		return brokerCommissionRate;
	}

	public void setBrokerCommissionRate(String brokerCommissionRate) {
		this.brokerCommissionRate = brokerCommissionRate;
	}

	public String getLeaderShare() {
		return leaderShare;
	}

	public void setLeaderShare(String leaderShare) {
		this.leaderShare = leaderShare;
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

}
