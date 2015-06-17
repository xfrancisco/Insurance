package org.mfi.service.info;

import java.util.List;

import org.mfi.data.Cli_client;

public interface IPopulationInfo {

	List<Cli_client> getClients();

	List<Cli_client> getBrokers();

	List<Cli_client> getInsurers();

	List<Cli_client> getReinsurers();

	List<Cli_client> getLawyers();

	List<Cli_client> getExperts();

	List<Cli_client> getThirdParties();

	List<Cli_client> getBeneficiaries();

	List<Cli_client> getAgencies();

	List<Cli_client> getAll();

	List<Cli_client> getClientsByName(String name);

	List<Cli_client> getBrokersByName(String name);

	List<Cli_client> getInsurersByName(String name);

	List<Cli_client> getReinsurersByName(String name);

	List<Cli_client> getExpertsByName(String name);

	List<Cli_client> getLawyersByName(String name);

	List<Cli_client> getBeneficiariesByName(String name);

	List<Cli_client> getThirdPartiesByName(String name);

	List<Cli_client> getAgenciesByName(String name);

	List<Cli_client> getAllByName(String name);

}
