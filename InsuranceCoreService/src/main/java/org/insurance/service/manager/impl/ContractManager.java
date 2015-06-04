package org.insurance.service.manager.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_contract;
import org.insurance.dto.contract.ContractDto;
import org.insurance.dto.contract.DispatchDto;
import org.insurance.dto.contract.GuaranteeDto;
import org.insurance.exception.InsuranceException;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICommonCheck;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IPremiumCheck;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.manager.IContractManager;
import org.springframework.stereotype.Service;

@Service
public class ContractManager extends ServiceCore implements IContractManager {

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IQuoteAndContractCheck quoteAndContractCheck;

	@Inject
	private IPremiumCheck premiumCheck;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private ICommonCheck commonCheck;

	@Override
	public int insertContract(String userId, long numcli, ContractDto contract) throws InsuranceException {
		personCheck.checkClient(numcli);

		Cli_contract cliContract = contract.getContract();
		personCheck.checkBroker(cliContract.getNumclibroker());
		personCheck.checkInsurer(cliContract.getNumclileader());
		premiumCheck.checkBranch(cliContract.getCbranch());
		premiumCheck.checkCategory(cliContract.getCbranch(), cliContract.getCcategory());
		quoteAndContractCheck.checkDuration(cliContract.getCduration());
		userCheck.checkUser(cliContract.getCuseruw());
		commonCheck.checkPeriod(cliContract.getStartval(), cliContract.getEndval());

		List<GuaranteeDto> guarantees = contract.getGuarantees();
		for (GuaranteeDto guaranteeDto : guarantees) {
			commonCheck.checkPercentage(guaranteeDto.getBrokerRate());
			premiumCheck.checkPremium(cliContract.getCbranch(), cliContract.getCcategory(), guaranteeDto.getCsection(), guaranteeDto.getCguarantee(),
					guaranteeDto.getCpremium());
			commonCheck.checkAmount(guaranteeDto.getGuaranteedAmount());
			commonCheck.checkAmount(guaranteeDto.getPremiumAmount());
			List<DispatchDto> dispatchDtos = guaranteeDto.getDispatch();
			for (DispatchDto dispatch : dispatchDtos) {
				commonCheck.checkPercentage(dispatch.getBrokerRate());
				commonCheck.checkPercentage(dispatch.getInsurerRate());
				commonCheck.checkPercentage(dispatch.getInsurerShare());
				personCheck.checkInsurer(dispatch.getNumcliinsurer());
			}
		}
		return 0;
	}

	@Override
	public int updateContract(String userId, Long personId, Integer contractId, ContractDto contract) throws InsuranceException {
		// TODO Auto-generated method stub
		return 0;
	}

}