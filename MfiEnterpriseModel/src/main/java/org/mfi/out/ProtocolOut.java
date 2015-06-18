package org.mfi.out;

import java.util.List;

public class ProtocolOut {

	private Long protocolId;
	private String name;
	private List<ProtocolInsurerOut> insurers;

	public Long getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProtocolInsurerOut> getInsurers() {
		return insurers;
	}

	public void setInsurers(List<ProtocolInsurerOut> insurers) {
		this.insurers = insurers;
	}

}