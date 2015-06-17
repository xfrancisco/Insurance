package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class NewPersonMovement extends PersonMovement {

	public NewPersonMovement(String ccivil, String name, String firstName, String companyName, String companyId) {
		super(MovementCode.NEWPERSON, ccivil, name, firstName, companyName, companyId);
	}

}
