package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class CategoryMovement extends Movement {

	protected CategoryMovement(MovementCode movement, String ccatcli) {
		super(movement);
		addParameters(Fields.CCATCLI, ccatcli);
	}

	protected static enum Fields {
		CCATCLI
	}

}
