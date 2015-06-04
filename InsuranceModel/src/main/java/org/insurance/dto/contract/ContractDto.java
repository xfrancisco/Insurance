package org.insurance.dto.contract;

import java.util.List;

import org.insurance.data.Cli_contract;

public class ContractDto {

	private Cli_contract contract;
	private List<GuaranteeDto> guarantees;

	public List<GuaranteeDto> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<GuaranteeDto> guarantees) {
		this.guarantees = guarantees;
	}

	public Cli_contract getContract() {
		return contract;
	}

	public void setContract(Cli_contract contract) {
		this.contract = contract;
	}

}
