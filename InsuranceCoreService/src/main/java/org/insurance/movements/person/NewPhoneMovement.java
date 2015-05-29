package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class NewPhoneMovement extends PhoneMovement {

	public NewPhoneMovement(final String cphone, final String phonenumber) {
		super(MovementCode.NEWPHONE, cphone, phonenumber);
	}

}
