package org.insurance.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.common.IContractService;
import org.insurance.dto.contract.ContractDto;
import org.insurance.exception.InsuranceException;
import org.insurance.in.ContractIn;
import org.insurance.in.UpdateContractIn;
import org.insurance.out.ContractOut;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.manager.IContractManager;
import org.insurance.utils.mapping.ContractMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ContractService implements IContractService {

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IContractManager contractManager;

	@Inject
	private IQuoteAndContractCheck quoteAndContractCheck;

	@Inject
	private IQuoteAndContractInfo quoteAndContractInfo;

	@Inject
	private IPersonCheck personCheck;

	@Override
	public ContractOut insertContract(String userId, ContractIn contractIn) throws InsuranceException {
		userCheck.checkUser(userId);
		ContractDto contract = ContractMapping.populateContractDto(contractIn);
		contractManager.insertContract(userId, contractIn.getPersonId(), contract);
		return null;
	}

	@Override
	public ContractOut updateContract(String userId, UpdateContractIn contractIn) throws InsuranceException {
		userCheck.checkUser(userId);
		ContractDto contract = null;
		contractManager.updateContract(userId, contractIn.getPersonId(), contractIn.getContractId(), contract);
		return null;
	}

	@Override
	public ContractOut getContract(String userId, long personId, int contractId) throws InsuranceException {
		userCheck.checkUser(userId);
		quoteAndContractCheck.checkContract(personId, contractId);
		return null;
	}

	@Override
	public List<ContractOut> getContracts(String userId, long personId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkClient(personId);
		quoteAndContractInfo.getContracts(personId);
		return null;
	}

}
