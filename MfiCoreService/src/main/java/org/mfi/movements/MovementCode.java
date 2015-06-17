package org.mfi.movements;

import org.mfi.movements.person.DelCategoryMovement;
import org.mfi.movements.person.DelMailMovement;
import org.mfi.movements.person.DelPhoneMovement;
import org.mfi.movements.person.ModAddressMovement;
import org.mfi.movements.person.ModMailMovement;
import org.mfi.movements.person.ModPersonMovement;
import org.mfi.movements.person.ModPhoneMovement;
import org.mfi.movements.person.ModQuoteMovement;
import org.mfi.movements.person.NewAddressMovement;
import org.mfi.movements.person.NewCategoryMovement;
import org.mfi.movements.person.NewMailMovement;
import org.mfi.movements.person.NewPersonMovement;
import org.mfi.movements.person.NewPhoneMovement;
import org.mfi.movements.person.NewQuoteMovement;
import org.mfi.movements.person.ValidQuoteMovement;

public enum MovementCode {
	//@formatter:off
	NEWPERSON(NewPersonMovement.class),
	MODPERSON(ModPersonMovement.class),
	NEWADDRESS(NewAddressMovement.class),
	MODADDRESS(ModAddressMovement.class),
	NEWPHONE(NewPhoneMovement.class),
	MODPHONE(ModPhoneMovement.class),
	DELPHONE(DelPhoneMovement.class),
	NEWMAIL(NewMailMovement.class),
	MODMAIL(ModMailMovement.class),
	DELMAIL(DelMailMovement.class),
	NEWCATCLI(NewCategoryMovement.class),
	DELCATCLI(DelCategoryMovement.class),
	NEWQUOTE(NewQuoteMovement.class),
	MODQUOTE(ModQuoteMovement.class),
	VALQUOTE(ValidQuoteMovement.class);
	//@formatter:on
	private Class<? extends Movement> mvtClass;

	MovementCode(Class<? extends Movement> mvtClass) {
		this.mvtClass = mvtClass;
	}

	public Class<? extends Movement> getMvtClass() {
		return this.mvtClass;
	}
}
