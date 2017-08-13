package com.example.exercisetracker.util.validation;

import com.example.exercisetracker.util.transfer.RegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterDTO> {
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {}

	@Override
	public boolean isValid(RegisterDTO value, ConstraintValidatorContext context) {
		boolean isValid = value.getPassword().equals(value.getPasswordConfirm());

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Passwords must match")
				.addPropertyNode("passwordConfirm").addConstraintViolation();

			return false;
		}

		return true;
	}
}
