package org.insurance.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.insurance.validation.constraints.impl.LengthValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { LengthValidator.class })
@ReportAsSingleViolation
public @interface Length {

	EnterpriseModelEnum min() default EnterpriseModelEnum.MIN;

	EnterpriseModelEnum max();

	String message() default "ERR_CLI_LENGTH_FIELD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
