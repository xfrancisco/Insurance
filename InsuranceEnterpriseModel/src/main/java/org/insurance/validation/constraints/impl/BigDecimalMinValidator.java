package org.insurance.validation.constraints.impl;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.insurance.validation.constraints.BigDecimalMin;

public class BigDecimalMinValidator implements ConstraintValidator<BigDecimalMin, String> {

	private static BigDecimal min = new BigDecimal(0);

	@Override
	public void initialize(BigDecimalMin annotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = new BigDecimal(value);
		} catch (Exception e) {
			return false;
		}
		int comparisonResult = bigDecimal.compareTo(min);
		return comparisonResult >= 0;
	}
}
