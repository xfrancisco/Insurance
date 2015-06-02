package org.insurance.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.insurance.validation.constraints.impl.PercentageValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { PercentageValidator.class })
public @interface Percentage {

	String message() default "ERR_CLI_PERCENTAGE_FIELD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
