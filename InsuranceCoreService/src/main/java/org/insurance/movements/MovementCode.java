package org.insurance.movements;

import org.insurance.movements.person.DelCategoryMovement;
import org.insurance.movements.person.DelMailMovement;
import org.insurance.movements.person.DelPhoneMovement;
import org.insurance.movements.person.ModAddressMovement;
import org.insurance.movements.person.ModMailMovement;
import org.insurance.movements.person.ModPersonMovement;
import org.insurance.movements.person.ModPhoneMovement;
import org.insurance.movements.person.ModQuoteMovement;
import org.insurance.movements.person.NewAddressMovement;
import org.insurance.movements.person.NewCategoryMovement;
import org.insurance.movements.person.NewMailMovement;
import org.insurance.movements.person.NewPersonMovement;
import org.insurance.movements.person.NewPhoneMovement;
import org.insurance.movements.person.NewQuoteMovement;

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
	MODQUOTE(ModQuoteMovement.class);
	//@formatter:on
	private Class<? extends Movement> mvtClass;

	MovementCode(Class<? extends Movement> mvtClass) {
		this.mvtClass = mvtClass;
	}

	public Class<? extends Movement> getMvtClass() {
		return this.mvtClass;
	}
}
