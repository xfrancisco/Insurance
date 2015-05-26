package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class ModMailMovement extends Movement {

	protected ModMailMovement(MovementCode movement) {
		super(MovementCode.MODMAIL);
	}

}
