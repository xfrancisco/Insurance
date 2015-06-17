package org.mfi.movements.person;

import org.mfi.movements.Movement;
import org.mfi.movements.MovementCode;

public class PhoneMovement extends Movement {

	protected PhoneMovement(final MovementCode movement, final String cphone, final String phonenumber) {
		super(movement);
		addParameters(Fields.PHONENUMBER, phonenumber);
		addParameters(Fields.CPHONE, cphone);
	}

	protected static enum Fields {
		PHONENUMBER,
		CPHONE;
	}

}
