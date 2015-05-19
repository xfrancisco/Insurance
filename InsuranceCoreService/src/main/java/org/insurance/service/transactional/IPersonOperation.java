package org.insurance.service.transactional;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;

public interface IPersonOperation {

	Long insertClient(Cli_client client);

	Long insertAddress(Cli_address address);
}
