package org.mfi.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.mfi.data.Cli_contract;
import org.mfi.dto.contract.ContractDto;
import org.mfi.dto.contract.DispatchDto;
import org.mfi.dto.contract.GuaranteeDto;
import org.mfi.exception.CommonException;
import org.mfi.in.ContractIn;
import org.mfi.in.DispatchIn;
import org.mfi.in.GuaranteeIn;
import org.mfi.out.ContractListOut;
import org.mfi.out.ContractOut;
import org.mfi.out.DispatchOut;
import org.mfi.out.GuaranteeOut;
import org.mfi.util.DateUtils;
import org.mfi.util.MappingUtils;

public final class ContractMapping {

	public static ContractDto populateContractDto(ContractIn contractIn) throws CommonException {
		ContractDto result = new ContractDto();
		result.setContract(populateContract(contractIn));
		result.setGuarantees(populateGuarantees(contractIn.getGuarantees()));
		result.setNumquote(contractIn.getQuoteId());
		return result;

	}

	private static Cli_contract populateContract(ContractIn contractIn) {
		Cli_contract contract = new Cli_contract();
		contract.setCbranch(contractIn.getBranchId());
		contract.setCcategory(contractIn.getCategoryId());
		contract.setCduration(contractIn.getDurationId());
		contract.setCfrequency(contractIn.getFrequencyId());
		contract.setCuseruw(contractIn.getUnderwriterId());
		contract.setStartval(DateUtils.parseStringToSqlDate(contractIn.getStartDate()));
		contract.setEndval(DateUtils.parseStringToSqlDate(contractIn.getEndDate()));
		contract.setNumclibroker(contractIn.getBrokerId());
		contract.setNumclileader(contractIn.getLeaderId());
		contract.setNumquote(contractIn.getQuoteId());

		return contract;
	}

	private static List<GuaranteeDto> populateGuarantees(List<GuaranteeIn> guaranteesIn) throws CommonException {
		List<GuaranteeDto> result = new ArrayList<GuaranteeDto>();
		for (GuaranteeIn guaranteeIn : guaranteesIn) {
			GuaranteeDto tmp = new GuaranteeDto();
			tmp.setBrokerRate(MappingUtils.toBigDecimal(guaranteeIn.getBrokerCommissionRate()));
			tmp.setCguarantee(guaranteeIn.getGuaranteeId());
			tmp.setCpremium(guaranteeIn.getPremiumId());
			tmp.setCsection(guaranteeIn.getSectionId());
			tmp.setCcategory(guaranteeIn.getCategoryId());
			tmp.setCbranch(guaranteeIn.getBranchId());
			tmp.setGuaranteedAmount(MappingUtils.toBigDecimal(guaranteeIn.getGuaranteedAmount()));
			tmp.setPremiumAmount(MappingUtils.toBigDecimal(guaranteeIn.getPremiumAmount()));
			tmp.setDispatch(populateDispatch(guaranteeIn.getDispatch()));
			tmp.setLeaderShare(MappingUtils.toBigDecimal(guaranteeIn.getLeaderShare()));
			result.add(tmp);
		}
		return result;

	}

	private static List<DispatchDto> populateDispatch(List<DispatchIn> dispatchesIn) throws CommonException {
		List<DispatchDto> result = new ArrayList<DispatchDto>();
		for (DispatchIn dispatchIn : dispatchesIn) {
			DispatchDto tmp = new DispatchDto();
			tmp.setInsurerRate(MappingUtils.toBigDecimal(dispatchIn.getInsurerCommissionRate()));
			tmp.setInsurerShare(MappingUtils.toBigDecimal(dispatchIn.getInsurerShare()));
			tmp.setNumcliinsurer(dispatchIn.getInsurerId());
			result.add(tmp);
		}

		return result;
	}

	public static List<ContractListOut> populateContractList(final List<Cli_contract> contracts) {
		List<ContractListOut> result = new ArrayList<ContractListOut>();
		for (Cli_contract cliContract : contracts) {
			result.add(populateContract(cliContract));
		}
		return result;
	}

	public static ContractListOut populateContract(final Cli_contract cliContract) {
		ContractListOut result = new ContractListOut();
		result.setPersonId(cliContract.getNumcli());
		result.setContractId(cliContract.getNumcon());
		result.setBranchId(cliContract.getCbranch());
		result.setCategoryId(cliContract.getCcategory());
		result.setUnderwriterId(cliContract.getCuseruw());
		result.setQuoteId(cliContract.getNumquote());
		return result;
	}

	public static ContractOut populateContractOut(final ContractDto contract) {
		ContractOut result = new ContractOut();
		result.setBranchId(contract.getContract().getCbranch());
		result.setBrokerId(contract.getContract().getNumclibroker());
		result.setCategoryId(contract.getContract().getCcategory());
		result.setDurationId(contract.getContract().getCduration());
		result.setEndDate(DateUtils.formatDate(contract.getContract().getEndval()));
		result.setGuarantees(populateGuaranteesOut(contract.getGuarantees()));
		result.setLeaderId(contract.getContract().getNumclileader());
		result.setPersonId(contract.getContract().getNumcli());
		result.setQuoteId(contract.getContract().getNumquote());
		result.setContractId(contract.getContract().getNumcon());
		result.setStartDate(DateUtils.formatDate(contract.getContract().getStartval()));
		result.setUnderwriterId(contract.getContract().getCuseruw());
		result.setFrequencyId(contract.getContract().getCfrequency());
		return result;
	}

	private static List<GuaranteeOut> populateGuaranteesOut(List<GuaranteeDto> guarantees) {
		List<GuaranteeOut> result = new ArrayList<GuaranteeOut>();
		for (GuaranteeDto guaranteeDto : guarantees) {
			GuaranteeOut tmp = new GuaranteeOut();
			tmp.setBrokerCommissionRate(MappingUtils.toString(guaranteeDto.getBrokerRate()));
			tmp.setGuaranteedAmount(MappingUtils.toString(guaranteeDto.getGuaranteedAmount()));
			tmp.setGuaranteeId(guaranteeDto.getCguarantee());
			tmp.setLeaderShare(MappingUtils.toString(guaranteeDto.getLeaderShare()));
			tmp.setPremiumAmount(MappingUtils.toString(guaranteeDto.getPremiumAmount()));
			tmp.setPremiumId(guaranteeDto.getCpremium());
			tmp.setSectionId(guaranteeDto.getCsection());
			tmp.setDispatch(populateDispatchOut(guaranteeDto.getDispatch()));
			result.add(tmp);
		}
		return result;
	}

	private static List<DispatchOut> populateDispatchOut(List<DispatchDto> dispatch) {

		List<DispatchOut> result = new ArrayList<DispatchOut>();
		for (DispatchDto dispatchDto : dispatch) {
			DispatchOut tmp = new DispatchOut();
			tmp.setInsurerCommissionRate(MappingUtils.toString(dispatchDto.getInsurerRate()));
			tmp.setInsurerId(dispatchDto.getNumcliinsurer());
			tmp.setInsurerShare(MappingUtils.toString(dispatchDto.getInsurerShare()));
			result.add(tmp);
		}
		return result;
	}
}
