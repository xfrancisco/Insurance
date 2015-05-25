package org.insurance.common;

import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.PersonOut;

public interface IPersonService {

	PersonOut insertPerson(String userId, InsertPersonIn personIn) throws InsuranceException;

	PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws InsuranceException;

	PersonOut getPerson(String userId, long personId) throws InsuranceException;

}
