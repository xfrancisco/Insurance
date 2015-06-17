package org.mfi.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.mfi.validation.constraints.impl.BigDecimalMinValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = BigDecimalMinValidator.class)
public @interface BigDecimalMin {

	String message() default "ERR_CLI_BIGDECIMALMIN_FIELD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
