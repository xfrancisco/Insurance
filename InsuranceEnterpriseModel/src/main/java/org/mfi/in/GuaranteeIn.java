package org.mfi.in;

import java.util.List;

import javax.validation.Valid;

import org.mfi.validation.constraints.BigDecimalMin;
import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;
import org.mfi.validation.constraints.Percentage;

import com.google.common.base.Strings;

public class GuaranteeIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.BRANCH)
	private String branchId;
	@Mandatory
	@Length(max = EnterpriseModelEnum.CATEGORY)
	private String categoryId;
	@Mandatory
	@Length(max = EnterpriseModelEnum.SECTIONID)
	private String sectionId;
	@Mandatory
	@Length(max = EnterpriseModelEnum.GUARANTEEID)
	private String guaranteeId;
	@Mandatory
	@Length(max = EnterpriseModelEnum.PREMIUMID)
	private String premiumId;

	@Mandatory
	@BigDecimalMin
	private String guaranteedAmount;

	@Mandatory
	@BigDecimalMin
	private String premiumAmount;

	@Percentage
	private String brokerCommissionRate;

	@Percentage
	private String leaderShare;

	@Valid
	private List<DispatchIn> dispatch;

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

	public List<DispatchIn> getDispatch() {
		return dispatch;
	}

	public void setDispatch(List<DispatchIn> dispatch) {
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
