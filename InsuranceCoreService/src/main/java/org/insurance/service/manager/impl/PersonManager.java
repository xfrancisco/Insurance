package org.insurance.service.manager.impl;

import javax.inject.Inject;

import org.insurance.service.ServiceCore;
import org.insurance.service.manager.IPersonManager;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonManager extends ServiceCore implements IPersonManager {

	@Inject
	private IPersonOperation personOperation;

	@Override
	public Long insertPerson() {
		return personOperation.insertClient();

	}

}