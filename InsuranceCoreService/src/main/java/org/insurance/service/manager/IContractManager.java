package org.insurance.service.manager;

import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.QuoteAndContractException;

public interface IContractManager {

	int insertContract(String userId, long numcli, ContractDto contract) throws QuoteAndContractException;

	int updateContract(String userId, Long personId, Integer contractId, ContractDto contract) throws QuoteAndContractException;

}
