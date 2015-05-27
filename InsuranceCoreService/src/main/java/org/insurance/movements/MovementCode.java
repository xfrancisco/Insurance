package org.insurance.movements;

import org.insurance.movements.person.DelCategoryMovement;
import org.insurance.movements.person.ModAddressMovement;
import org.insurance.movements.person.ModMailMovement;
import org.insurance.movements.person.ModPersonMovement;
import org.insurance.movements.person.ModTelMovement;
import org.insurance.movements.person.NewAddressMovement;
import org.insurance.movements.person.NewCategoryMovement;
import org.insurance.movements.person.NewMailMovement;
import org.insurance.movements.person.NewPersonMovement;
import org.insurance.movements.person.NewTelMovement;

public enum MovementCode {
	//@formatter:off
	NEWPERSON(NewPersonMovement.class),
	MODPERSON(ModPersonMovement.class),
	NEWADDRESS(NewAddressMovement.class),
	MODADDRESS(ModAddressMovement.class),
	NEWTEL(NewTelMovement.class),
	MODTEL(ModTelMovement.class),
	NEWMAIL(NewMailMovement.class),
	MODMAIL(ModMailMovement.class), 
	NEWCATCLI(NewCategoryMovement.class),
	DELCATCLI(DelCategoryMovement.class);
	//@formatter:on
	private Class<? extends Movement> mvtClass;

	MovementCode(Class<? extends Movement> mvtClass) {
		this.mvtClass = mvtClass;
	}

	public Class<? extends Movement> getMvtClass() {
		return this.mvtClass;
	}
}
