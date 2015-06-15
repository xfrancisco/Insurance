package org.mfi.movements.person;

import org.mfi.movements.MovementCode;

public class ModAddressMovement extends AddressMovement {

	public ModAddressMovement(String caddress, String street1, String street2, String street3, String street4, String cpostal, String city,
			String ccountry) {
		super(MovementCode.MODADDRESS, caddress, street1, street2, street3, street4, cpostal, city, ccountry);
	}

	public void setOldValues(String caddress, String street1, String street2, String street3, String street4, String cpostal, String city,
			String ccountry) {
		addOldParameters(Fields.CADDRESS, caddress);
		addOldParameters(Fields.STREET1, street1);
		addOldParameters(Fields.STREET2, street2);
		addOldParameters(Fields.STREET3, street3);
		addOldParameters(Fields.STREET4, street4);
		addOldParameters(Fields.CPOSTAL, cpostal);
		addOldParameters(Fields.CITY, city);
		addOldParameters(Fields.CCOUNTRY, ccountry);
	}
}
