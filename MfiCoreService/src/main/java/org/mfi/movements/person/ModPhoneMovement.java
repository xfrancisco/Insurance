package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class ModPhoneMovement extends PhoneMovement {

	public ModPhoneMovement(final String cphone, final String phonenumber) {
		super(MovementCode.MODPHONE, cphone, phonenumber);
	}

	public void setOldValues(final String cphone, final String phonenumber) {
		addOldParameters(Fields.CPHONE, cphone);
		addOldParameters(Fields.PHONENUMBER, phonenumber);
	}

}
