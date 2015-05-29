package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class DelPhoneMovement extends PhoneMovement {

	public DelPhoneMovement(final String cphone) {
		super(MovementCode.DELPHONE, cphone, null);
	}

	public void setOldValues(final String cphone, final String phonenumber) {
		addOldParameters(Fields.CPHONE, cphone);
		addOldParameters(Fields.PHONENUMBER, phonenumber);
	}

}
