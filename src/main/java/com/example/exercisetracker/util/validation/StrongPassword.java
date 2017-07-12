package com.example.exercisetracker.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StrongPasswordValidator.class)
@Documented
public @interface StrongPassword {
	String message() default "Password does not meet requirements";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}