package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.PopulationIn;
import org.insurance.out.PersonOut;

public interface IPopulationService {

	List<PersonOut> getClients(String userId) throws InsuranceException;

	List<PersonOut> getBrokers(String userId) throws InsuranceException;

	List<PersonOut> getInsurers(String userId) throws InsuranceException;

	List<PersonOut> getReinsurers(String userId) throws InsuranceException;

	List<PersonOut> getExperts(String userId) throws InsuranceException;

	List<PersonOut> getLawyers(String userId) throws InsuranceException;

	List<PersonOut> getBeneficiaries(String userId) throws InsuranceException;

	List<PersonOut> getThirdParties(String userId) throws InsuranceException;

	List<PersonOut> getAgencies(String userId) throws InsuranceException;

	List<PersonOut> getPopulations(String userId, PopulationIn populationIn, String name) throws InsuranceException;;

	List<PersonOut> getClientsByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getBrokersByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getInsurersbyName(String userId, String name) throws InsuranceException;

	List<PersonOut> getReinsurersByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getExpertsByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getLawyersByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getBeneficiariesByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getThirdPartiesByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getAgenciesByName(String userId, String name) throws InsuranceException;

	List<PersonOut> getAll(String userId) throws InsuranceException;

	List<PersonOut> getAllByName(String userId, String name) throws InsuranceException;

}
