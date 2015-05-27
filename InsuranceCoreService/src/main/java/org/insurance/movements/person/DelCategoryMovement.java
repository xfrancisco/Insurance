package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class DelCategoryMovement extends CategoryMovement {

	public DelCategoryMovement(String ccatcli) {
		super(MovementCode.DELCATCLI, ccatcli);
	}

}
