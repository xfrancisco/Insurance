package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfi.common.IContractService;
import org.mfi.data.Cli_contract;
import org.mfi.dto.contract.ContractDto;
import org.mfi.exception.MfcException;
import org.mfi.in.ContractIn;
import org.mfi.in.UpdateContractIn;
import org.mfi.out.ContractListOut;
import org.mfi.out.ContractOut;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.manager.IContractManager;
import org.mfi.utils.mapping.ContractMapping;
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
	public ContractOut insertContract(String userId, ContractIn contractIn) throws MfcException {
		userCheck.checkUser(userId);
		ContractDto contract = ContractMapping.populateContractDto(contractIn);
		contractManager.insertContract(userId, contractIn.getPersonId(), contract);
		return ContractMapping.populateContractOut(contract);
	}

	@Override
	public ContractOut updateContract(String userId, UpdateContractIn contractIn) throws MfcException {
		userCheck.checkUser(userId);
		ContractDto contract = null;
		contractManager.updateContract(userId, contractIn.getPersonId(), contractIn.getContractId(), contract);
		return ContractMapping.populateContractOut(contract);
	}

	@Override
	public ContractOut getContract(String userId, long personId, int contractId) throws MfcException {
		userCheck.checkUser(userId);
		quoteAndContractCheck.checkContract(personId, contractId);
		ContractDto contract = quoteAndContractInfo.getContractDto(personId, contractId);
		return ContractMapping.populateContractOut(contract);
	}

	@Override
	public List<ContractListOut> getContracts(String userId, long personId) throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkClient(personId);
		List<Cli_contract> contracts = quoteAndContractInfo.getContracts(personId);
		return ContractMapping.populateContractList(contracts);
	}

}
