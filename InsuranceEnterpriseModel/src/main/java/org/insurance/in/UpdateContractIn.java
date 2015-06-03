package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

public class UpdateContractIn extends ContractIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.PERSONID)
	private Integer contractId;

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

}