package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.AddressHistoryOut;
import org.insurance.out.EmailHistoryOut;
import org.insurance.out.PersonOut;
import org.insurance.out.PhoneHistoryOut;

public interface IPersonService {

	PersonOut insertPerson(String userId, InsertPersonIn personIn) throws InsuranceException;

	PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws InsuranceException;

	PersonOut getPerson(String userId, long personId) throws InsuranceException;

	List<EmailHistoryOut> getEmailHistory(String userId, Long personId) throws InsuranceException;

	List<PhoneHistoryOut> getPhoneHistory(String userId, Long personId) throws InsuranceException;

	List<AddressHistoryOut> getAddressHistory(String userId, Long personId) throws InsuranceException;

}
