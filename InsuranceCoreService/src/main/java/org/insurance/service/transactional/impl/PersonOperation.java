package org.insurance.service.transactional.impl;

import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonOperation extends ServiceCore implements IPersonOperation {

	@Override
	public Long insertClient() {
		return 1L;
	}

}
