package com.example.exercisetracker.util.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
	@Override
	public void initialize(StrongPassword constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		LengthRule lengthRule = new LengthRule(12, 50); // Password must be at least 12 characters long.

		CharacterCharacteristicsRule variableRule = new CharacterCharacteristicsRule();
		variableRule.setRules(Arrays.asList(
			new CharacterRule(EnglishCharacterData.UpperCase, 1),
			new CharacterRule(EnglishCharacterData.LowerCase, 1),
			new CharacterRule(EnglishCharacterData.Digit, 1),
			new CharacterRule(EnglishCharacterData.Special, 1)
		));
		variableRule.setNumberOfCharacteristics(3); // Password must meet at least 3 out of 4 rules.

		PasswordValidator validator = new PasswordValidator(Arrays.asList(lengthRule, variableRule));
		RuleResult result = validator.validate(new PasswordData(value));

		if (!result.isValid()) {
			context.disableDefaultConstraintViolation();
			for (String message : validator.getMessages(result)) {
				context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			}
			return false;
		}

		return true;
	}
}
