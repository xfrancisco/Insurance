package org.mfi.service.info.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cli_quote;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.data.Cpt_guardispatch;
import org.mfi.dto.contract.ContractDto;
import org.mfi.dto.contract.DispatchDto;
import org.mfi.dto.contract.GuaranteeDto;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IContractPremiumInfo;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.springframework.stereotype.Service;

@Service
public class QuoteAndContractInfo extends ServiceCore implements IQuoteAndContractInfo {

	@Inject
	private IContractPremiumInfo contractPremiumInfo;

	@Override
	public List<Cod_quotestatus> getQuoteStatus() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_quotestatus.class);
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public int getNextNumQuote(final long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.setProjection(Projections.max("numquote"));
		Integer result = genericDao.getFirstByCriteria(criteria);
		if (result == null)
			return 0;
		else
			return result + 1;
	}

	@Override
	public Cli_quote getQuote(final long numcli, final int numquote) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("numquote", numquote));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_quote> getQuotes(final long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_quote.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cod_duration getDuration(final String cduration) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_duration.class);
		criteria.add(Restrictions.eq("cduration", cduration));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cod_quotestatus getQuoteStatus(final String cquotestatus) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_quotestatus.class);
		criteria.add(Restrictions.eq("cquotestatus", cquotestatus));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public Cli_contract getContract(final long numcli, final int numcon) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_contract.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.add(Restrictions.eq("numcon", numcon));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cli_contract> getContracts(final long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_contract.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public int getNextNumContract(final long numcli) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cli_contract.class);
		criteria.add(Restrictions.eq("numcli", numcli));
		criteria.setProjection(Projections.max("numcon"));
		Integer result = genericDao.getFirstByCriteria(criteria);
		if (result == null)
			return 0;
		else
			return result + 1;
	}

	@Override
	public Cod_quotestatus getValidatedQuoteStatus() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_quotestatus.class);
		criteria.add(Restrictions.eq("indvali", "1"));
		criteria.add(Restrictions.eq("indvalidated", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public ContractDto getContractDto(final long numcli, final int numcon) {
		ContractDto result = new ContractDto();
		Cli_contract contract = getContract(numcli, numcon);
		result.setContract(contract);
		result.setGuarantees(getGuaranteesDto(numcli, numcon, contract.getNumclibroker(), contract.getNumclileader()));
		return result;

	}

	private List<GuaranteeDto> getGuaranteesDto(final long numcli, final int numcon, final long numclibroker, final long numclileader) {
		List<GuaranteeDto> result = new ArrayList<GuaranteeDto>();
		List<Cli_guarantee> guarantees = contractPremiumInfo.getGuarantees(numcli, numcon);
		for (Cli_guarantee cliGuarantee : guarantees) {
			GuaranteeDto guaranteeDto = new GuaranteeDto();
			guaranteeDto.setCcategory(cliGuarantee.getCcategory());
			guaranteeDto.setCbranch(cliGuarantee.getCbranch());
			guaranteeDto.setCguarantee(cliGuarantee.getCguarantee());
			guaranteeDto.setCpremium(cliGuarantee.getCpremium());
			guaranteeDto.setCsection(cliGuarantee.getCsection());
			guaranteeDto.setGuaranteedAmount(cliGuarantee.getGuaranteeamount());
			Cpt_guarcommi cptGuarcommiBroker = contractPremiumInfo.getBrokerCommission(cliGuarantee.getNumguarantee(), numclibroker);
			Cpt_guardispatch cptGuarDispatchLeader = contractPremiumInfo.getLeaderShare(cliGuarantee.getNumguarantee(), numclileader);
			guaranteeDto.setBrokerRate(cptGuarcommiBroker.getRate());
			guaranteeDto.setLeaderShare(cptGuarDispatchLeader.getSharepart());
			guaranteeDto.setDispatch(getDispatchDto(cliGuarantee.getNumguarantee(), numclileader));
			guaranteeDto.setPremiumAmount(cliGuarantee.getPremiumamount());

		}
		return result;
	}

	private List<DispatchDto> getDispatchDto(final long numguarantee, final long numclileader) {
		List<DispatchDto> result = new ArrayList<DispatchDto>();
		List<Cpt_guardispatch> dispatches = contractPremiumInfo.getDispatches(numguarantee, numclileader);
		for (Cpt_guardispatch dispatch : dispatches) {
			DispatchDto tmp = new DispatchDto();
			Cpt_guarcommi commi = contractPremiumInfo.getInsurerCommission(numguarantee, dispatch.getNumcliins());
			tmp.setInsurerRate(commi.getRate());
			tmp.setInsurerShare(dispatch.getSharepart());
			tmp.setNumcliinsurer(dispatch.getNumcliins());
			result.add(tmp);
		}
		return result;
	}

	@Override
	public Cod_frequency getFrequency(String cfrequency) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_frequency.class);
		criteria.add(Restrictions.eq("cfrequency", cfrequency));
		criteria.add(Restrictions.eq("indvali", "1"));
		return genericDao.getFirstByCriteria(criteria);
	}

}
