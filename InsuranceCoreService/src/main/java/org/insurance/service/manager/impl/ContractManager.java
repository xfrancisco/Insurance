package org.insurance.service.manager.impl;

import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.QuoteAndContractException;
import org.insurance.service.ServiceCore;
import org.insurance.service.manager.IContractManager;
import org.springframework.stereotype.Service;

@Service
public class ContractManager extends ServiceCore implements IContractManager {

	@Override
	public int insertContract(String userId, long numcli, ContractDto contract) throws QuoteAndContractException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateContract(String userId, Long personId, Integer contractId, ContractDto contract) throws QuoteAndContractException {
		// TODO Auto-generated method stub
		return 0;
	}

}