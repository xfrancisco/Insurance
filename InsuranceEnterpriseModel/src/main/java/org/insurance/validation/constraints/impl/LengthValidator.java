package org.insurance.validation.constraints.impl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;

public class LengthValidator implements ConstraintValidator<Length, Object> {

	private int min = EnterpriseModelEnum.MIN.length;
	private int max = EnterpriseModelEnum.MAX.length;

	@Override
	public void initialize(Length annotation) {
		this.min = annotation.min().length;
		this.max = annotation.max().length;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			if (min == EnterpriseModelEnum.MIN.length)
				return true;
			else
				return false;
		}

		if (value instanceof List) {
			List<?> tmp = (List<?>) value;
			for (Object object : tmp) {
				boolean result = isValid(object, context);
				if (!result)
					return false;
			}
		} else {
			String valueStr = String.valueOf(value);
			return isValid(valueStr);
		}

		return true;
	}

	private boolean isValid(String value) {
		if (value.length() < min || value.length() > max) {
			return false;
		}
		return true;
	}
}
