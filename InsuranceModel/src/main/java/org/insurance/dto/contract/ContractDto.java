package org.insurance.dto.contract;

import java.util.List;

public class ContractDto {

	private List<GuaranteeDto> guarantees;
	private List<Long> insurers;

	public List<GuaranteeDto> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<GuaranteeDto> guarantees) {
		this.guarantees = guarantees;
	}

	public List<Long> getInsurers() {
		return insurers;
	}

	public void setInsurers(List<Long> insurers) {
		this.insurers = insurers;
	}

}
