package org.insurance.common;

import org.insurance.InsuranceEnterpriseModel.in.PersonIn;
import org.insurance.InsuranceEnterpriseModel.out.PersonOut;

public interface IPersonService {

	PersonOut insertPerson(PersonIn clientIn);

	PersonOut getPerson(long personId);

}
