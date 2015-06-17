package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class NewPhoneMovement extends PhoneMovement {

	public NewPhoneMovement(final String cphone, final String phonenumber) {
		super(MovementCode.NEWPHONE, cphone, phonenumber);
	}

}
