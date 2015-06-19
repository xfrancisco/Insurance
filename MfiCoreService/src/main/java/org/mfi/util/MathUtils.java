package org.mfi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {

	private MathUtils() {

	}

	private static final BigDecimal ONEHUNDERT = new BigDecimal(100);

	public static BigDecimal applyPercentage(final BigDecimal value, final BigDecimal percentage) {
		return value.multiply(percentage).divide(ONEHUNDERT, 2, RoundingMode.HALF_UP);

	}

	public static boolean equals(final BigDecimal value, final int other) {
		BigDecimal tmp = new BigDecimal(other);
		return equals(value, tmp);
	}

	private static boolean equals(BigDecimal value, BigDecimal other) {
		if (value.compareTo(other) == 0)
			return true;
		return false;
	}

}
