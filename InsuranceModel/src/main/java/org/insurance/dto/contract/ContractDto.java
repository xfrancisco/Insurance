package org.insurance.dto.contract;

import java.util.List;

import org.insurance.data.Cli_contract;

public class ContractDto {

	private Cli_contract contract;
	private Integer numquote;
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

	public Integer getNumquote() {
		return numquote;
	}

	public void setNumquote(Integer numquote) {
		this.numquote = numquote;
	}

}
