package org.insurance.common;

import org.insurance.exception.InsuranceException;
import org.insurance.in.PersonIn;
import org.insurance.out.PersonOut;

public interface IPersonService {

	PersonOut insertPerson(PersonIn clientIn) throws InsuranceException;

	PersonOut getPerson(long personId) throws InsuranceException;

}
