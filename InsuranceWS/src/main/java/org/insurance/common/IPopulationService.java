package org.insurance.common;

import java.util.List;

import org.insurance.exception.UserException;
import org.insurance.in.PopulationIn;
import org.insurance.out.PersonOut;

public interface IPopulationService {

	List<PersonOut> getPopulations(String userId, PopulationIn populationIn) throws UserException;

	List<PersonOut> getClients(String userId) throws UserException;

	List<PersonOut> getBrokers(String userId) throws UserException;

	List<PersonOut> getInsurers(String userId) throws UserException;

	List<PersonOut> getReinsurers(String userId) throws UserException;

	List<PersonOut> getExperts(String userId) throws UserException;

	List<PersonOut> getLawyers(String userId) throws UserException;

	List<PersonOut> getBeneficiaries(String userId) throws UserException;

	List<PersonOut> getThirdParties(String userId) throws UserException;

	List<PersonOut> getAgencies(String userId) throws UserException;

}
