package org.mfi.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.mfi.validation.constraints.impl.MandatoryValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { MandatoryValidator.class })
public @interface Mandatory {

	String message() default "ERR_CLI_MANDATORY_FIELD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
