package org.mfi.utils.mapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mfi.data.Cli_contract;
import org.mfi.data.Cpt_fee;
import org.mfi.dto.contract.AgencyPlacementDto;
import org.mfi.dto.contract.ContractDto;
import org.mfi.dto.contract.GuaranteeDto;
import org.mfi.dto.contract.InsurerDispatchDto;
import org.mfi.exception.CommonException;
import org.mfi.in.ContractIn;
import org.mfi.in.ContractInsurerIn;
import org.mfi.in.DispatchIn;
import org.mfi.in.GuaranteeIn;
import org.mfi.out.codes.GuaranteeOut;
import org.mfi.out.contract.ContractInsurerOut;
import org.mfi.out.contract.ContractListOut;
import org.mfi.out.contract.ContractOut;
import org.mfi.out.contract.DispatchOut;
import org.mfi.util.DateUtils;
import org.mfi.util.DateUtils.DatePattern;
import org.mfi.util.DateUtils.TimePeriod;
import org.mfi.util.MappingUtils;

import com.google.common.base.Strings;

public final class ContractMapping {

	public static ContractDto populateContractDto(ContractIn contractIn) throws CommonException {
		ContractDto result = new ContractDto();
		result.setContract(populateContract(contractIn));
		result.setGuarantees(populateGuarantees(contractIn.getCoinsurers(), contractIn.getGuarantees(), contractIn.getBrokerCommissionRate(),
				contractIn.getLeaderShare(), contractIn.getLeadingCommissionRate()));
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
		String renewalDate = contractIn.getRenewalDate();
		if (!Strings.isNullOrEmpty(renewalDate)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(contract.getStartval());
			int year = cal.get(Calendar.YEAR);
			renewalDate += "/" + year;
			Date renewal = DateUtils.parseStringToSqlDate(renewalDate, DatePattern.DATE_DD_MM_YYYY);
			if (renewal.before(contract.getStartval()))
				renewal = DateUtils.addToDate(renewal, 1, TimePeriod.YEAR);
			contract.setRenewalDate(DateUtils.convertUtilDateToSqlDate(renewal));
			contract.setEndval(contract.getRenewalDate());
		}
		contract.setNumclibroker(contractIn.getBrokerId());
		contract.setNumclileader(contractIn.getLeaderId());
		contract.setNumquote(contractIn.getQuoteId());

		return contract;
	}

	private static List<GuaranteeDto> populateGuarantees(List<ContractInsurerIn> insurers, List<GuaranteeIn> guaranteesIn,
			String brokerCommissionRate, String leaderShare, String leadingCommissionRate) throws CommonException {
		List<GuaranteeDto> result = new ArrayList<GuaranteeDto>();
		for (GuaranteeIn guaranteeIn : guaranteesIn) {
			GuaranteeDto tmp = new GuaranteeDto();
			tmp.setBrokerRate(MappingUtils.toBigDecimal(brokerCommissionRate));
			tmp.setCguarantee(guaranteeIn.getGuaranteeId());
			tmp.setCpremium(guaranteeIn.getPremiumId());
			tmp.setCsection(guaranteeIn.getSectionId());
			tmp.setCcategory(guaranteeIn.getCategoryId());
			tmp.setCbranch(guaranteeIn.getBranchId());
			tmp.setGuaranteedAmount(MappingUtils.toBigDecimal(guaranteeIn.getGuaranteedAmount()));
			tmp.setPremiumAmount(MappingUtils.toBigDecimal(guaranteeIn.getPremiumAmount()));
			tmp.setLeaderShare(MappingUtils.toBigDecimal(leaderShare));
			tmp.setLeadingCommissionRate(MappingUtils.toBigDecimal(leadingCommissionRate));
			tmp.setInsurerDispatch(populateDispatch(insurers));
			tmp.setAgencyPlacement(populateAgencyPlacement(guaranteeIn.getDispatch()));
			result.add(tmp);
		}
		return result;

	}

	private static List<AgencyPlacementDto> populateAgencyPlacement(List<DispatchIn> dispatch) throws CommonException {
		List<AgencyPlacementDto> result = new ArrayList<AgencyPlacementDto>();
		for (DispatchIn dispatchIn : dispatch) {
			AgencyPlacementDto tmp = new AgencyPlacementDto();
			tmp.setAgencyCommissionRate(MappingUtils.toBigDecimal(dispatchIn.getAgencyCommissionRate()));
			tmp.setInsurerShare(MappingUtils.toBigDecimal(dispatchIn.getInsurerShare()));
			tmp.setNumcliinsurer(dispatchIn.getInsurerId());
			result.add(tmp);
		}
		return result;
	}

	private static List<InsurerDispatchDto> populateDispatch(List<ContractInsurerIn> insurersIn) throws CommonException {
		List<InsurerDispatchDto> result = new ArrayList<InsurerDispatchDto>();
		for (ContractInsurerIn insurerIn : insurersIn) {
			InsurerDispatchDto tmp = new InsurerDispatchDto();
			tmp.setInsurerShare(MappingUtils.toBigDecimal(insurerIn.getInsurerShare()));
			tmp.setNumcliinsurer(insurerIn.getInsurerId());
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
		result.setPolicyFees(populatePolicyFees(contract.getFee()));
		result.setRenewalDate(DateUtils.formatDate(contract.getContract().getRenewalDate()));

		GuaranteeDto firstGuarantee = contract.getGuarantees().isEmpty() ? null : contract.getGuarantees().get(0);
		if (firstGuarantee != null) {
			result.setBrokerCommissionRate(MappingUtils.toString(firstGuarantee.getBrokerRate()));
			result.setLeaderShare(MappingUtils.toString(firstGuarantee.getLeaderShare()));
			result.setLeadingCommissionRate(MappingUtils.toString(firstGuarantee.getLeadingCommissionRate()));
			result.setCoinsurers(populateContractInsurersOut(firstGuarantee.getInsurerDispatch()));
		}
		return result;
	}

	private static List<ContractInsurerOut> populateContractInsurersOut(List<InsurerDispatchDto> insurerDispatch) {
		List<ContractInsurerOut> result = new ArrayList<ContractInsurerOut>();
		for (InsurerDispatchDto insurerDispatchDto : insurerDispatch) {
			ContractInsurerOut tmp = new ContractInsurerOut();
			tmp.setInsurerId(insurerDispatchDto.getNumcliinsurer());
			tmp.setInsurerShare(MappingUtils.toString(insurerDispatchDto.getInsurerShare()));
			result.add(tmp);
		}
		return result;
	}

	private static String populatePolicyFees(Cpt_fee fee) {
		if (fee != null)
			return MappingUtils.toString(fee.getAmount());
		return null;
	}

	private static List<GuaranteeOut> populateGuaranteesOut(List<GuaranteeDto> guarantees) {
		List<GuaranteeOut> result = new ArrayList<GuaranteeOut>();
		for (GuaranteeDto guaranteeDto : guarantees) {
			GuaranteeOut tmp = new GuaranteeOut();
			tmp.setGuaranteedAmount(MappingUtils.toString(guaranteeDto.getGuaranteedAmount()));
			tmp.setGuaranteeId(guaranteeDto.getCguarantee());
			tmp.setPremiumAmount(MappingUtils.toString(guaranteeDto.getPremiumAmount()));
			tmp.setPremiumId(guaranteeDto.getCpremium());
			tmp.setSectionId(guaranteeDto.getCsection());
			tmp.setBranchId(guaranteeDto.getCbranch());
			tmp.setCategoryId(guaranteeDto.getCcategory());
			tmp.setDispatch(populateDispatchOut(guaranteeDto.getAgencyPlacement()));
			result.add(tmp);
		}
		return result;
	}

	private static List<DispatchOut> populateDispatchOut(List<AgencyPlacementDto> placement) {

		List<DispatchOut> result = new ArrayList<DispatchOut>();
		for (AgencyPlacementDto placementDto : placement) {
			DispatchOut tmp = new DispatchOut();
			tmp.setAgencyCommissionRate(MappingUtils.toString(placementDto.getAgencyCommissionRate()));
			tmp.setInsurerId(placementDto.getNumcliinsurer());
			tmp.setInsurerShare(MappingUtils.toString(placementDto.getInsurerShare()));
			result.add(tmp);
		}
		return result;
	}
}
