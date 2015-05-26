package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class PersonMovement extends Movement {

	protected PersonMovement(MovementCode movement, String ccivil, String name, String firstName, String companyName, String companyId) {
		super(movement);
		addParameters(Fields.CCIVIL, ccivil);
		addParameters(Fields.NAME, name);
		addParameters(Fields.FIRSTNAME, firstName);
		addParameters(Fields.COMPANYID, companyId);
		addParameters(Fields.COMPANYNAME, companyName);
	}

	protected static enum Fields {
		CCIVIL,
		NAME,
		FIRSTNAME,
		COMPANYNAME,
		COMPANYID
	}

}
