package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.ContractIn;
import org.mfi.in.UpdateContractIn;
import org.mfi.out.ContractListOut;
import org.mfi.out.ContractOut;

public interface IContractService {

	ContractOut insertContract(String userId, ContractIn newContractIn) throws MfcException;

	ContractOut updateContract(String userId, UpdateContractIn updateContractIn) throws MfcException;

	ContractOut getContract(String userId, long personId, int contractId) throws MfcException;

	List<ContractListOut> getContracts(String userId, long personId) throws MfcException;

}
