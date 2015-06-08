package org.insurance.util;

import java.math.BigDecimal;

public final class MathUtils {

	private MathUtils() {

	}

	private static final BigDecimal ONEHUNDERT = new BigDecimal(100);

	public static BigDecimal applyPercentage(final BigDecimal value, final BigDecimal percentage) {
		return value.multiply(percentage).divide(ONEHUNDERT);

	}

}
