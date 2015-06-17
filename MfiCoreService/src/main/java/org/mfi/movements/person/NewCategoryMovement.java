package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class NewCategoryMovement extends CategoryMovement {

	public NewCategoryMovement(String ccatcli) {
		super(MovementCode.NEWCATCLI, ccatcli);
	}

}
