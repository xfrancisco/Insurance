package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class NewPersonMovement extends PersonMovement {

	public NewPersonMovement(String ccivil, String name, String firstName, String companyName, String companyId) {
		super(MovementCode.NEWPERSON, ccivil, name, firstName, companyName, companyId);
	}

}
