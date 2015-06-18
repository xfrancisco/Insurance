package org.mfi.in;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;
import org.mfi.validation.constraints.Percentage;

public class ProtocolInsurerIn {
	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private Long insurerId;

	@Mandatory
	@Percentage
	private String insurerShare;

	@Mandatory
	@Percentage
	private String insurerRate;

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerShare() {
		return insurerShare;
	}

	public void setInsurerShare(String insurerShare) {
		this.insurerShare = insurerShare;
	}

	public String getInsurerRate() {
		return insurerRate;
	}

	public void setInsurerRate(String insurerRate) {
		this.insurerRate = insurerRate;
	}

}
