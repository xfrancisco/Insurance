package org.insurance.common.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.InsuranceEnterpriseModel.in.ClientIn;
import org.insurance.InsuranceEnterpriseModel.out.ClientOut;
import org.insurance.common.IPersonService;
import org.insurance.service.manager.IPersonManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PersonService implements IPersonService {

	static final Logger logger = Logger.getLogger(PersonService.class);

	@Inject
	private IPersonManager personManager;

	@Override
	public ClientOut insertClient(ClientIn clientIn) {
		personManager.insertPerson();
		return new ClientOut();
	}

}
