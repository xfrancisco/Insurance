package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class NewTelMovement extends Movement {

	protected NewTelMovement(MovementCode movement) {
		super(MovementCode.NEWTEL);
	}

}
