package org.insurance.common.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IPopulationService;
import org.insurance.data.Cli_client;
import org.insurance.exception.UserException;
import org.insurance.in.PopulationIn;
import org.insurance.out.PersonOut;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IPopulationInfo;
import org.insurance.utils.mapping.PersonMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PopulationService implements IPopulationService {

	static final Logger logger = Logger.getLogger(PopulationService.class);

	@Inject
	private IPopulationInfo populationInfo;

	@Inject
	private IUserCheck userCheck;

	@Override
	public List<PersonOut> getPopulations(String userId, PopulationIn populationIn) throws UserException {
		userCheck.checkUser(userId);
		Set<Cli_client> clients = new HashSet<Cli_client>();
		if (populationIn != null) {
			if (populationIn.isClient())
				clients.addAll(populationInfo.getClients());
			if (populationIn.isBroker())
				clients.addAll(populationInfo.getBrokers());
			if (populationIn.isInsurer())
				clients.addAll(populationInfo.getInsurers());
			if (populationIn.isReinsurer())
				clients.addAll(populationInfo.getReinsurers());
			if (populationIn.isExpert())
				clients.addAll(populationInfo.getExperts());
			if (populationIn.isLawyer())
				clients.addAll(populationInfo.getLawyers());
			if (populationIn.isBeneficiary())
				clients.addAll(populationInfo.getBeneficiaries());
			if (populationIn.isThirdParty())
				clients.addAll(populationInfo.getThirdParties());
			if (populationIn.isAgency())
				clients.addAll(populationInfo.getAgencies());
		}
		return PersonMapping.populatePersonOut(clients);
	}

	@Override
	public List<PersonOut> getClients(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getClients());
	}

	@Override
	public List<PersonOut> getBrokers(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBrokers());
	}

	@Override
	public List<PersonOut> getInsurers(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getInsurers());
	}

	@Override
	public List<PersonOut> getReinsurers(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getReinsurers());
	}

	@Override
	public List<PersonOut> getExperts(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getExperts());
	}

	@Override
	public List<PersonOut> getLawyers(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getLawyers());
	}

	@Override
	public List<PersonOut> getBeneficiaries(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getBeneficiaries());
	}

	@Override
	public List<PersonOut> getThirdParties(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getThirdParties());
	}

	@Override
	public List<PersonOut> getAgencies(String userId) throws UserException {
		userCheck.checkUser(userId);
		return PersonMapping.populatePersonOut(populationInfo.getAgencies());
	}
}
