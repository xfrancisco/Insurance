package org.insurance.service.info;

import java.util.List;

import org.insurance.data.Cli_client;

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

}
