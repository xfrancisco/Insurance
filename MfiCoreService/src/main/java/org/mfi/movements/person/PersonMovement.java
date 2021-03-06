package org.mfi.movements.person;

import org.mfi.movements.Movement;
import org.mfi.movements.MovementCode;

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
