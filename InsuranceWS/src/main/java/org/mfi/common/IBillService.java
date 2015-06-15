package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.out.BillOut;

public interface IBillService {

	List<BillOut> getBills(String userId, long personId, int contractId) throws MfcException;

}
