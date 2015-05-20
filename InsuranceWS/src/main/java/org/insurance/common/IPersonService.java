package org.insurance.common;

import org.insurance.InsuranceEnterpriseModel.in.PersonIn;
import org.insurance.InsuranceEnterpriseModel.out.PersonOut;
import org.insurance.exception.InsuranceException;

public interface IPersonService {

	PersonOut insertPerson(PersonIn clientIn) throws InsuranceException;

	PersonOut getPerson(long personId) throws InsuranceException;

}
