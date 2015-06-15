package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class DelMailMovement extends MailMovement {

	public DelMailMovement() {
		super(MovementCode.DELMAIL, null, null);
	}

	public void setOldValues(final String cemail, final String email) {
		addOldParameters(Fields.CEMAIL, cemail);
		addOldParameters(Fields.EMAIL, email);
	}

}
