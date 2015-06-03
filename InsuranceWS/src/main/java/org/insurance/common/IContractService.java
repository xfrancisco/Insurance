package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.ContractIn;
import org.insurance.out.ContractOut;

public interface IContractService {

	ContractOut insertContract(String userId, ContractIn newContractIn) throws InsuranceException;

	ContractOut updateContract(String userId, ContractIn updateContractIn) throws InsuranceException;

	ContractOut getContract(String userId, long personId, int contractId) throws InsuranceException;

	List<ContractOut> getContracts(String userId, long personId) throws InsuranceException;

}
