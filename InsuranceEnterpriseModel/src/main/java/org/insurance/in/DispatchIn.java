package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Percentage;

import com.google.common.base.Strings;

public class DispatchIn {

	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long insurerId;

	@Percentage
	private String insurerShare;

	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long brokerId;

	@Percentage
	private String brokerShare;

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public String getInsurerShare() {
		return Strings.nullToEmpty(insurerShare).toUpperCase().trim();
	}

	public void setInsurerShare(String insurerShare) {
		this.insurerShare = insurerShare;
	}

	public String getBrokerShare() {
		return Strings.nullToEmpty(brokerShare).toUpperCase().trim();
	}

	public void setBrokerShare(String brokerShare) {
		this.brokerShare = brokerShare;
	}

}
