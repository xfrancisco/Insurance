package org.mfi.movements.person;

import org.mfi.movements.Movement;
import org.mfi.movements.MovementCode;

public class MailMovement extends Movement {

	protected MailMovement(MovementCode movement, final String cemail, final String email) {
		super(movement);
		addParameters(Fields.EMAIL, email);
		addParameters(Fields.CEMAIL, cemail);
	}

	protected static enum Fields {
		EMAIL,
		CEMAIL;
	}

}
