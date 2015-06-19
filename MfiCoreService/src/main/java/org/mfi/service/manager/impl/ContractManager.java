package org.mfi.service.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mfi.conf.Cod_duration;
import org.mfi.data.Cli_contract;
import org.mfi.dto.contract.AgencyPlacementDto;
import org.mfi.dto.contract.ContractDto;
import org.mfi.dto.contract.GuaranteeDto;
import org.mfi.dto.contract.InsurerDispatchDto;
import org.mfi.exception.MfcException;
import org.mfi.exception.PremiumException;
import org.mfi.exception.PremiumException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.ICommonCheck;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IPremiumCheck;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.manager.IContractManager;
import org.mfi.service.transactional.IContractOperation;
import org.mfi.service.transactional.IQuoteOperation;
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
	public int insertContract(final String cuser, final long numcli, ContractDto contract) throws MfcException {
		personCheck.checkClient(numcli);
		Cli_contract cliContract = contract.getContract();
		personCheck.checkBroker(cliContract.getNumclibroker());
		personCheck.checkInsurer(cliContract.getNumclileader());
		premiumCheck.checkBranch(cliContract.getCbranch());
		premiumCheck.checkCategory(cliContract.getCbranch(), cliContract.getCcategory());
		Cod_duration codDuration = quoteAndContractCheck.checkDuration(cliContract.getCduration());
		quoteAndContractCheck.checkFrequency(cliContract.getCfrequency());
		quoteAndContractCheck.checkDurationAndDates(codDuration, cliContract.getRenewalDate(), cliContract.getEndval());
		if (contract.getNumquote() != null)
			quoteAndContractCheck.checkQuote(numcli, contract.getNumquote());
		userCheck.checkUser(cliContract.getCuseruw());
		commonCheck.checkPeriod(cliContract.getStartval(), cliContract.getEndval());

		List<GuaranteeDto> guarantees = contract.getGuarantees();
		for (GuaranteeDto guaranteeDto : guarantees) {
			List<Long> insurers = new ArrayList<Long>();
			insurers.add(cliContract.getNumclileader());
			premiumCheck.checkPremium(guaranteeDto.getCbranch(), guaranteeDto.getCcategory(), guaranteeDto.getCsection(),
					guaranteeDto.getCguarantee(), guaranteeDto.getCpremium());
			commonCheck.checkAmount(guaranteeDto.getGuaranteedAmount());
			commonCheck.checkAmount(guaranteeDto.getPremiumAmount());
			commonCheck.checkPercentage(guaranteeDto.getLeaderShare());
			commonCheck.checkPercentage(guaranteeDto.getBrokerRate());
			BigDecimal globalShare = new BigDecimal(0);
			globalShare = globalShare.add(guaranteeDto.getLeaderShare());

			List<InsurerDispatchDto> dispatchDtos = guaranteeDto.getInsurerDispatch();
			for (InsurerDispatchDto dispatch : dispatchDtos) {
				commonCheck.checkPercentage(dispatch.getInsurerShare());
				personCheck.checkInsurer(dispatch.getNumcliinsurer());
				globalShare = globalShare.add(dispatch.getInsurerShare());
				if (insurers.contains(dispatch.getNumcliinsurer()))
					throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_INSURER_ALREADY_IN_SHARE, guaranteeDto.getCpremium(),
							dispatch.getNumcliinsurer());
				else
					insurers.add(dispatch.getNumcliinsurer());
			}
			commonCheck.checkShareOnPremium(globalShare, guaranteeDto.getCpremium());

			List<AgencyPlacementDto> placements = guaranteeDto.getAgencyPlacement();
			globalShare = new BigDecimal(0);
			for (AgencyPlacementDto agencyPlacementDto : placements) {
				commonCheck.checkPercentage(agencyPlacementDto.getAgencyCommissionRate());
				commonCheck.checkPercentage(agencyPlacementDto.getInsurerShare());
				personCheck.checkInsurer(agencyPlacementDto.getNumcliinsurer());
				globalShare = globalShare.add(agencyPlacementDto.getInsurerShare());
			}
			commonCheck.checkShareOnPremium(globalShare, guaranteeDto.getCpremium());
		}

		/* Insertion du contrat */
		int numcon = quoteInfo.getNextNumContract(numcli);
		contractOperation.insertContract(numcli, numcon, cuser, contract);

		/* Validation de la proposition */
		if (contract.getNumquote() != null) {
			quoteOperation.validateQuote(cuser, numcli, contract.getNumquote(), numcon);
		}
		return numcon;
	}

	@Override
	public int updateContract(String cuser, Long personId, Integer contractId, ContractDto contract) throws MfcException {
		// TODO Auto-generated method stub
		return 0;
	}

}