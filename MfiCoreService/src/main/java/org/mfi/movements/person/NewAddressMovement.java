package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class NewAddressMovement extends AddressMovement {

	public NewAddressMovement(String caddress, String street1, String street2, String street3, String street4, String cpostal, String city,
			String ccountry) {
		super(MovementCode.NEWADDRESS, caddress, street1, street2, street3, street4, cpostal, city, ccountry);
	}

}
