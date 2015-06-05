package org.insurance.service.manager;

import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.InsuranceException;

public interface IContractManager {

	int insertContract(String cuser, long numcli, ContractDto contract) throws InsuranceException;

	int updateContract(String cuser, Long personId, Integer contractId, ContractDto contract) throws InsuranceException;

}
