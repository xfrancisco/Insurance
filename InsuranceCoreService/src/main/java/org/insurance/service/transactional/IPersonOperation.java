package org.insurance.service.transactional;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;

public interface IPersonOperation {

	Long insertClient(String cuser, Cli_client client);

	Long insertAddress(String cuser, Cli_address address);

	Long updateClient(String cuser, Cli_client client);

	Long updateAddress(String cuser, Cli_address address);
}
