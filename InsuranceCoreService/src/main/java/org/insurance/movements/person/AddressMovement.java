package org.insurance.movements.person;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;

public class AddressMovement extends Movement {

	protected AddressMovement(MovementCode movement, String street1, String street2, String street3, String street4, String cpostal, String city,
			String ccountry) {
		super(movement);
		addParameters(Fields.STREET1, street1);
		addParameters(Fields.STREET2, street2);
		addParameters(Fields.STREET3, street3);
		addParameters(Fields.STREET4, street4);
		addParameters(Fields.CPOSTAL, cpostal);
		addParameters(Fields.CITY, city);
		addParameters(Fields.CCOUNTRY, ccountry);
	}

	public static enum Fields {
		STREET1,
		STREET2,
		STREET3,
		STREET4,
		CPOSTAL,
		CITY,
		CCOUNTRY;
	}

}
