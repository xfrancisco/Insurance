package org.insurance.service.transactional.impl;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonOperation extends ServiceCore implements IPersonOperation {

	@Override
	public Long insertClient(final Cli_client client) {
		return (Long) genericDao.save(client);
	}

	@Override
	public Long insertAddress(Cli_address address) {
		return (Long) genericDao.save(address);
	}

}
