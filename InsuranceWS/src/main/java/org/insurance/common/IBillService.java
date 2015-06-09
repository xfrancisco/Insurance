package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.out.BillOut;

public interface IBillService {

	List<BillOut> getBills(String userId, long personId, int contractId) throws InsuranceException;

}
