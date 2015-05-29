package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class DelPhoneMovement extends PhoneMovement {

	public DelPhoneMovement() {
		super(MovementCode.DELPHONE, null, null);
	}

	public void setOldValues(final String cphone, final String phonenumber) {
		addOldParameters(Fields.CPHONE, cphone);
		addOldParameters(Fields.PHONENUMBER, phonenumber);
	}

}
