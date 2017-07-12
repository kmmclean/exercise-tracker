package com.example.exercisetracker.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {
	String message() default "Password and password confirmation must match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}