package org.insurance.service.manager.impl;

import java.math.BigDecimal;
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
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.manager.IContractManager;
import org.insurance.service.transactional.IContractOperation;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class ContractManager extends ServiceCore implements IContractManager {

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IQuoteAndContractCheck quoteAndContractCheck;

	@Inject
	private IQuoteAndContractInfo quoteInfo;

	@Inject
	private IPremiumCheck premiumCheck;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private ICommonCheck commonCheck;

	@Inject
	private IContractOperation contractOperation;

	@Inject
	private IQuoteOperation quoteOperation;

	@Override
	public int insertContract(String cuser, long numcli, ContractDto contract) throws InsuranceException {
		personCheck.checkClient(numcli);

		Cli_contract cliContract = contract.getContract();
		personCheck.checkBroker(cliContract.getNumclibroker());
		personCheck.checkInsurer(cliContract.getNumclileader());
		premiumCheck.checkBranch(cliContract.getCbranch());
		premiumCheck.checkCategory(cliContract.getCbranch(), cliContract.getCcategory());
		quoteAndContractCheck.checkDuration(cliContract.getCduration());
		if (contract.getNumquote() != null)
			quoteAndContractCheck.checkQuote(numcli, contract.getNumquote());
		userCheck.checkUser(cliContract.getCuseruw());
		commonCheck.checkPeriod(cliContract.getStartval(), cliContract.getEndval());

		List<GuaranteeDto> guarantees = contract.getGuarantees();
		for (GuaranteeDto guaranteeDto : guarantees) {
			premiumCheck.checkPremium(cliContract.getCbranch(), cliContract.getCcategory(), guaranteeDto.getCsection(), guaranteeDto.getCguarantee(),
					guaranteeDto.getCpremium());
			commonCheck.checkAmount(guaranteeDto.getGuaranteedAmount());
			commonCheck.checkAmount(guaranteeDto.getPremiumAmount());
			commonCheck.checkPercentage(guaranteeDto.getLeaderShare());
			commonCheck.checkPercentage(guaranteeDto.getBrokerRate());
			BigDecimal globalShare = new BigDecimal(0);
			globalShare = globalShare.add(guaranteeDto.getLeaderShare());
			List<DispatchDto> dispatchDtos = guaranteeDto.getDispatch();
			for (DispatchDto dispatch : dispatchDtos) {
				commonCheck.checkPercentage(dispatch.getInsurerRate());
				commonCheck.checkPercentage(dispatch.getInsurerShare());
				personCheck.checkInsurer(dispatch.getNumcliinsurer());
				globalShare = globalShare.add(dispatch.getInsurerShare());
			}
			commonCheck.checkShareOnPremium(globalShare, guaranteeDto.getCpremium());
		}
		int numcon = quoteInfo.getNextNumContract(numcli);
		contractOperation.insertContract(numcli, numcon, cuser, contract);
		if (contract.getNumquote() != null) {
			quoteOperation.validateQuote(cuser, numcli, contract.getNumquote(), numcon);
		}
		return numcon;
	}

	@Override
	public int updateContract(String cuser, Long personId, Integer contractId, ContractDto contract) throws InsuranceException {
		// TODO Auto-generated method stub
		return 0;
	}

}