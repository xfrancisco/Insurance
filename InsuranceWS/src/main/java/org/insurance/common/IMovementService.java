package org.insurance.common;

import org.insurance.exception.InsuranceException;
import org.insurance.out.MovementDetailsOut;

public interface IMovementService {

	MovementDetailsOut getMovement(String userId, long movementId) throws InsuranceException;

}
