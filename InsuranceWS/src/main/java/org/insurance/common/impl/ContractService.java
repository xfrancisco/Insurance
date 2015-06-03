package org.insurance.common.impl;

import java.util.List;

import org.insurance.common.IContractService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.ContractIn;
import org.insurance.out.ContractOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ContractService implements IContractService {

	@Override
	public ContractOut insertContract(String userId, ContractIn newContractIn) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractOut updateContract(String userId, ContractIn updateContractIn) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractOut getContract(String userId, long personId, int contractId) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractOut> getContracts(String userId, long personId) throws InsuranceException {
		// TODO Auto-generated method stub
		return null;
	}

}
