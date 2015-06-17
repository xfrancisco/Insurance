package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.out.movements.MovementDetailsOut;
import org.mfi.out.movements.MovementOut;

public interface IMovementService {

	List<MovementOut> getMovements(String userId, long personId, Integer contractId, Integer quoteId) throws MfcException;

	MovementDetailsOut getMovement(String userId, long movementId) throws MfcException;

}
