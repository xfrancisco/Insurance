package org.mfi.common;

import org.mfi.exception.MfcException;
import org.mfi.out.billing.GlobalBillOut;

public interface IBillService {

	GlobalBillOut getBills(String userId, long personId, int contractId) throws MfcException;

}
