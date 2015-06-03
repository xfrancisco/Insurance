package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.out.movements.MovementDetailsOut;
import org.insurance.out.movements.MovementOut;

public interface IMovementService {

	List<MovementOut> getMovements(String userId, long personId, Integer contractId, Integer quoteId) throws InsuranceException;

	MovementDetailsOut getMovement(String userId, long movementId) throws InsuranceException;

}
