package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class DelCategoryMovement extends CategoryMovement {

	public DelCategoryMovement(String ccatcli) {
		super(MovementCode.DELCATCLI, ccatcli);
	}

}
