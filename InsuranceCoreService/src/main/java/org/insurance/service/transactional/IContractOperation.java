package org.insurance.service.transactional;

import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.InsuranceException;

public interface IContractOperation {

	int insertContract(long numcli, int numcon, String cuser, ContractDto contract) throws InsuranceException;

}
