package org.mfi.dto.contract;

import java.util.List;

import org.mfi.data.Cli_contract;
import org.mfi.data.Cpt_fee;

public class ContractDto {

	private Cli_contract contract;
	private Integer numquote;
	private Cpt_fee fee;
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

	public Cpt_fee getFee() {
		return fee;
	}

	public void setFee(Cpt_fee fee) {
		this.fee = fee;
	}

}
