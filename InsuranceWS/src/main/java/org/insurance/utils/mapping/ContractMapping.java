package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.data.Cli_contract;
import org.insurance.dto.contract.ContractDto;
import org.insurance.dto.contract.DispatchDto;
import org.insurance.dto.contract.GuaranteeDto;
import org.insurance.exception.CommonException;
import org.insurance.in.ContractIn;
import org.insurance.in.DispatchIn;
import org.insurance.in.GuaranteeIn;
import org.insurance.util.DateUtils;
import org.insurance.util.MappingUtils;

public final class ContractMapping {

	public static ContractDto populateContractDto(ContractIn contractIn) throws CommonException {
		ContractDto result = new ContractDto();
		result.setContract(populateContract(contractIn));
		result.setGuarantees(populateGuarantees(contractIn.getGuarantees()));
		return result;

	}

	private static Cli_contract populateContract(ContractIn contractIn) {
		Cli_contract contract = new Cli_contract();
		contract.setCbranch(contractIn.getBranchId());
		contract.setCcategory(contractIn.getCategoryId());
		contract.setCduration(contractIn.getDurationId());
		contract.setCuseruw(contractIn.getUnderwriterId());
		contract.setStartval(DateUtils.parseStringToSqlDate(contractIn.getStartDate()));
		contract.setEndval(DateUtils.parseStringToSqlDate(contractIn.getEndDate()));
		contract.setNumclibroker(contractIn.getBrokerId());
		contract.setNumclileader(contractIn.getLeaderId());

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
			tmp.setBrokerRate(MappingUtils.toBigDecimal(dispatchIn.getInsurerCommissionRate()));
			tmp.setInsurerRate(MappingUtils.toBigDecimal(dispatchIn.getInsurerCommissionRate()));
			tmp.setInsurerShare(MappingUtils.toBigDecimal(dispatchIn.getInsurerShare()));
			tmp.setNumcliinsurer(dispatchIn.getInsurerId());
			result.add(tmp);
		}

		return result;
	}

}
