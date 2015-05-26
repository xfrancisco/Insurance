package org.insurance.movements.person;

import org.insurance.movements.MovementCode;

public class NewAddressMovement extends AddressMovement {

	public NewAddressMovement(String street1, String street2, String street3, String street4, String cpostal, String city, String ccountry) {
		super(MovementCode.NEWADDRESS, street1, street2, street3, street4, cpostal, city, ccountry);
	}

}
