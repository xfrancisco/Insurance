package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class NewMailMovement extends MailMovement {

	public NewMailMovement(final String cemail, final String email) {
		super(MovementCode.NEWMAIL, cemail, email);
	}

}
