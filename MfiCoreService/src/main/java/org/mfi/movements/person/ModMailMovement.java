package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class ModMailMovement extends MailMovement {

	public ModMailMovement(final String cemail, final String email) {
		super(MovementCode.MODMAIL, cemail, email);
	}

	public void setOldValues(final String cemail, final String email) {
		addOldParameters(Fields.CEMAIL, cemail);
		addOldParameters(Fields.EMAIL, email);
	}

}
