package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class ModPersonMovement extends PersonMovement {

	public ModPersonMovement(String ccivil, String name, String firstName, String companyName, String companyId) {
		super(MovementCode.MODPERSON, ccivil, name, firstName, companyName, companyId);
	}

	public void setOldValues(String ccivil, String name, String firstName, String companyName, String companyId) {
		addOldParameters(Fields.CCIVIL, ccivil);
		addOldParameters(Fields.NAME, name);
		addOldParameters(Fields.FIRSTNAME, firstName);
		addOldParameters(Fields.COMPANYID, companyId);
		addOldParameters(Fields.COMPANYNAME, companyName);
	}

}
