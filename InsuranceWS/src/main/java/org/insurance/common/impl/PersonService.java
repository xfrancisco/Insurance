package org.insurance.common.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.InsuranceEnterpriseModel.in.PersonIn;
import org.insurance.InsuranceEnterpriseModel.out.PersonOut;
import org.insurance.common.IPersonService;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_client;
import org.insurance.exception.InsuranceException;
import org.insurance.exception.PersonException;
import org.insurance.exception.PersonException.ErrorCode;
import org.insurance.service.info.IPersonInfo;
import org.insurance.service.manager.IPersonManager;
import org.insurance.utils.mapping.PersonMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PersonService implements IPersonService {

	static final Logger logger = Logger.getLogger(PersonService.class);

	@Inject
	private IPersonManager personManager;

	@Inject
	private IPersonInfo personInfo;

	@Override
	public PersonOut insertPerson(PersonIn personIn) throws InsuranceException {
		Cli_client client = PersonMapping.populateClient(personIn);
		Cli_address address = PersonMapping.populateAddress(personIn.getAddress());
		personManager.insertPerson(client, address);
		return PersonMapping.populatePersonOut(client, address);
	}

	@Override
	public PersonOut getPerson(long personId) throws InsuranceException {
		Cli_client client = personInfo.getPerson(personId);
		if (client == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_PERSON);
		Cli_address address = personInfo.getAddress(personId);
		return PersonMapping.populatePersonOut(client, address);
	}
}
