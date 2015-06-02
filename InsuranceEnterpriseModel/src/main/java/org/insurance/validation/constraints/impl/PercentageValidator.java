package org.insurance.validation.constraints.impl;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.insurance.validation.constraints.Percentage;

import com.google.common.base.Strings;

public class PercentageValidator implements ConstraintValidator<Percentage, String> {

	private static BigDecimal max = new BigDecimal(100);

	@Override
	public void initialize(Percentage annotation) {

	}

	@Override
	public boolean isValid(String percentage, ConstraintValidatorContext context) {
		if (Strings.isNullOrEmpty(percentage))
			return true;

		BigDecimal bigDecimal = null;
		try {
			bigDecimal = new BigDecimal(percentage);
		} catch (Exception e) {
			return false;
		}
		int comparisonResult = bigDecimal.compareTo(max);
		return comparisonResult <= 0;
	}
}
