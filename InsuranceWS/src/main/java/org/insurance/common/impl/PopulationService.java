package org.insurance.common.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IPopulationService;
import org.insurance.data.Cli_client;
import org.insurance.exception.InsuranceException;
import org.insurance.in.PopulationIn;
import org.insurance.out.PersonOut;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IPopulationInfo;
import org.insurance.utils.mapping.PersonMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PopulationService implements IPopulationService {

	static final Logger logger = Logger.getLogger(PopulationService.class);

	@Inject
	private IPopulationInfo populationInfo;

	@Inject
	private IUserCheck userCheck;

	@Override
	public List<PersonOut> getPopulations(final String userId, PopulationIn populationIn, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		Set<Cli_client> clients = new HashSet<Cli_client>();
		if (populationIn != null) {
			if (Strings.isNullOrEmpty(name)) {
				if (populationIn.getIsClient())
					clients.addAll(populationInfo.getClients());
				if (populationIn.getIsBroker())
					clients.addAll(populationInfo.getBrokers());
				if (populationIn.getIsInsurer())
					clients.addAll(populationInfo.getInsurers());
				if (populationIn.getIsReinsurer())
					clients.addAll(populationInfo.getReinsurers());
				if (populationIn.getIsExpert())
					clients.addAll(populationInfo.getExperts());
				if (populationIn.getIsLawyer())
					clients.addAll(populationInfo.getLawyers());
				if (populationIn.getIsBeneficiary())
					clients.addAll(populationInfo.getBeneficiaries());
				if (populationIn.getIsThirdParty())
					clients.addAll(populationInfo.getThirdParties());
				if (populationIn.getIsAgency())
					clients.addAll(populationInfo.getAgencies());
			} else {
				if (populationIn.getIsClient())
					clients.addAll(populationInfo.getClientsByName(name));
				if (populationIn.getIsBroker())
					clients.addAll(populationInfo.getBrokersByName(name));
				if (populationIn.getIsInsurer())
					clients.addAll(populationInfo.getInsurersByName(name));
				if (populationIn.getIsReinsurer())
					clients.addAll(populationInfo.getReinsurersByName(name));
				if (populationIn.getIsExpert())
					clients.addAll(populationInfo.getExpertsByName(name));
				if (populationIn.getIsLawyer())
					clients.addAll(populationInfo.getLawyersByName(name));
				if (populationIn.getIsBeneficiary())
					clients.addAll(populationInfo.getBeneficiariesByName(name));
				if (populationIn.getIsThirdParty())
					clients.addAll(populationInfo.getThirdPartiesByName(name));
				if (populationIn.getIsAgency())
					clients.addAll(populationInfo.getAgenciesByName(name));
			}
		}
		return PersonMapping.populatePersonOut(clients);
	}

	@Override
	public List<PersonOut> getClients(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getClients());
	}

	@Override
	public List<PersonOut> getBrokers(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBrokers());
	}

	@Override
	public List<PersonOut> getInsurers(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getInsurers());
	}

	@Override
	public List<PersonOut> getReinsurers(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getReinsurers());
	}

	@Override
	public List<PersonOut> getExperts(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getExperts());
	}

	@Override
	public List<PersonOut> getLawyers(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getLawyers());
	}

	@Override
	public List<PersonOut> getBeneficiaries(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBeneficiaries());
	}

	@Override
	public List<PersonOut> getThirdParties(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getThirdParties());
	}

	@Override
	public List<PersonOut> getAgencies(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getAgencies());
	}

	@Override
	public List<PersonOut> getClientsByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getClients(userId);
		return PersonMapping.populatePersonOut(populationInfo.getClientsByName(name));
	}

	@Override
	public List<PersonOut> getBrokersByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getBrokers(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBrokersByName(name));
	}

	@Override
	public List<PersonOut> getInsurersbyName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getInsurers(userId);
		return PersonMapping.populatePersonOut(populationInfo.getInsurersByName(name));
	}

	@Override
	public List<PersonOut> getReinsurersByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getReinsurers(userId);
		return PersonMapping.populatePersonOut(populationInfo.getReinsurersByName(name));
	}

	@Override
	public List<PersonOut> getExpertsByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getExperts(userId);
		return PersonMapping.populatePersonOut(populationInfo.getExpertsByName(name));
	}

	@Override
	public List<PersonOut> getLawyersByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getLawyers(userId);
		return PersonMapping.populatePersonOut(populationInfo.getLawyersByName(name));
	}

	@Override
	public List<PersonOut> getBeneficiariesByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getBeneficiaries(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBeneficiariesByName(name));
	}

	@Override
	public List<PersonOut> getThirdPartiesByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getThirdParties(userId);
		return PersonMapping.populatePersonOut(populationInfo.getThirdPartiesByName(name));
	}

	@Override
	public List<PersonOut> getAgenciesByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		if (Strings.isNullOrEmpty(name))
			return getAgencies(userId);
		return PersonMapping.populatePersonOut(populationInfo.getAgenciesByName(name));
	}

	@Override
	public List<PersonOut> getAll(final String userId) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getAll());
	}

	@Override
	public List<PersonOut> getAllByName(final String userId, final String name) throws InsuranceException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getAllByName(name));
	}
}
