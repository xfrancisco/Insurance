package org.mfi.movements.person;

import org.mfi.movements.Movement;
import org.mfi.movements.MovementCode;

public class CategoryMovement extends Movement {

	protected CategoryMovement(MovementCode movement, String ccatcli) {
		super(movement);
		addParameters(Fields.CCATCLI, ccatcli);
	}

	protected static enum Fields {
		CCATCLI
	}

}
