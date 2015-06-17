package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class NewMailMovement extends MailMovement {

	public NewMailMovement(final String cemail, final String email) {
		super(MovementCode.NEWMAIL, cemail, email);
	}

}
