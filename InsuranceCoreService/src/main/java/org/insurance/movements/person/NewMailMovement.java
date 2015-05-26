package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class NewMailMovement extends Movement {

	protected NewMailMovement(MovementCode movement) {
		super(MovementCode.NEWMAIL);
	}

}
