package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.InsertPersonIn;
import org.mfi.in.UpdatePersonIn;
import org.mfi.out.person.AddressHistoryOut;
import org.mfi.out.person.EmailHistoryOut;
import org.mfi.out.person.PersonOut;
import org.mfi.out.person.PhoneHistoryOut;

public interface IPersonService {

	PersonOut insertPerson(String userId, InsertPersonIn personIn) throws MfcException;

	PersonOut updatePerson(String userId, UpdatePersonIn personIn) throws MfcException;

	PersonOut getPerson(String userId, long personId) throws MfcException;

	List<EmailHistoryOut> getEmailHistory(String userId, Long personId) throws MfcException;

	List<PhoneHistoryOut> getPhoneHistory(String userId, Long personId) throws MfcException;

	List<AddressHistoryOut> getAddressHistory(String userId, Long personId) throws MfcException;

}
