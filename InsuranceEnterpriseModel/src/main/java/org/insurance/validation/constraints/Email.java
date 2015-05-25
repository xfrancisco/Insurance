package org.insurance.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.insurance.validation.constraints.impl.EmailValidator;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

	String message() default "ERR_CLI_INVALID_EMAIL";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
