package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class NewCategoryMovement extends CategoryMovement {

	public NewCategoryMovement(String ccatcli) {
		super(MovementCode.NEWCATCLI, ccatcli);
	}

}
