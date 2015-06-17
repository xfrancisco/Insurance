package org.mfi.service.transactional;

import org.mfi.movements.Movement;

public interface IMovementOperation {

	long insertMovement(long numcli, Integer numcon, Integer numquote, String cuser, Movement movement);
}
