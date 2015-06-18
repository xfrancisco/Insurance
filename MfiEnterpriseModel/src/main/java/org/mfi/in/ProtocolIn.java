package org.mfi.in;

import java.util.List;

import javax.validation.Valid;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;

public class ProtocolIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.PROTOCOLNAME)
	private String name;

	@Mandatory
	@Valid
	private List<ProtocolInsurerIn> insurers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProtocolInsurerIn> getInsurers() {
		return insurers;
	}

	public void setInsurers(List<ProtocolInsurerIn> insurers) {
		this.insurers = insurers;
	}

}