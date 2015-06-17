package org.mfi.service.transactional;

import org.mfi.dto.contract.ContractDto;
import org.mfi.exception.MfcException;

public interface IContractOperation {

	int insertContract(long numcli, int numcon, String cuser, ContractDto contract) throws MfcException;

}
