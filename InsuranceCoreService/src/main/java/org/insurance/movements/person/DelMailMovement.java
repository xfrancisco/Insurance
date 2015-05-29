package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class DelMailMovement extends MailMovement {

	public DelMailMovement(final String cemail) {
		super(MovementCode.DELMAIL, cemail, null);
	}

	public void setOldValues(final String cemail, final String email) {
		addOldParameters(Fields.CEMAIL, cemail);
		addOldParameters(Fields.EMAIL, email);
	}

}
