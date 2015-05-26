package org.insurance.service.transactional;

import org.insurance.movements.Movement;

public interface IMovementOperation {

	long insertMovement(long numcli, Integer numcon, String cuser, Movement movement);
}
