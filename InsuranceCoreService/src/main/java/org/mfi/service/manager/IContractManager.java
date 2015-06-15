package org.mfi.service.manager;

import org.mfi.dto.contract.ContractDto;
import org.mfi.exception.MfcException;

public interface IContractManager {

	int insertContract(String cuser, long numcli, ContractDto contract) throws MfcException;

	int updateContract(String cuser, Long personId, Integer contractId, ContractDto contract) throws MfcException;

}
