package org.insurance.out;

import java.math.BigDecimal;

public class TaxOut extends CodeTableOut {

	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
