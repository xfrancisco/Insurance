package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.PopulationIn;
import org.mfi.out.PersonOut;

public interface IPopulationService {

	List<PersonOut> getClients(String userId) throws MfcException;

	List<PersonOut> getBrokers(String userId) throws MfcException;

	List<PersonOut> getInsurers(String userId) throws MfcException;

	List<PersonOut> getReinsurers(String userId) throws MfcException;

	List<PersonOut> getExperts(String userId) throws MfcException;

	List<PersonOut> getLawyers(String userId) throws MfcException;

	List<PersonOut> getBeneficiaries(String userId) throws MfcException;

	List<PersonOut> getThirdParties(String userId) throws MfcException;

	List<PersonOut> getAgencies(String userId) throws MfcException;

	List<PersonOut> getPopulations(String userId, PopulationIn populationIn, String name) throws MfcException;;

	List<PersonOut> getClientsByName(String userId, String name) throws MfcException;

	List<PersonOut> getBrokersByName(String userId, String name) throws MfcException;

	List<PersonOut> getInsurersbyName(String userId, String name) throws MfcException;

	List<PersonOut> getReinsurersByName(String userId, String name) throws MfcException;

	List<PersonOut> getExpertsByName(String userId, String name) throws MfcException;

	List<PersonOut> getLawyersByName(String userId, String name) throws MfcException;

	List<PersonOut> getBeneficiariesByName(String userId, String name) throws MfcException;

	List<PersonOut> getThirdPartiesByName(String userId, String name) throws MfcException;

	List<PersonOut> getAgenciesByName(String userId, String name) throws MfcException;

	List<PersonOut> getAll(String userId) throws MfcException;

	List<PersonOut> getAllByName(String userId, String name) throws MfcException;

}
