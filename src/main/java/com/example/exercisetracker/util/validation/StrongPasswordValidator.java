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
		CharacterCharacteristicsRule characteristicsRule = new CharacterCharacteristicsRule();
		characteristicsRule.setNumberOfCharacteristics(3);
		characteristicsRule.setRules(Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				new CharacterRule(EnglishCharacterData.Special, 1),
				new CharacterRule(EnglishCharacterData.Digit, 1)
		));

		PasswordValidator validator = new PasswordValidator(Arrays.asList(
			new LengthRule(12, 50),
			characteristicsRule
		));

		RuleResult result = validator.validate(new PasswordData(value));

		if (!result.isValid()) {
			context.disableDefaultConstraintViolation();
			validator.getMessages(result).stream().forEach(message ->
				context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
			);

			return false;
		}

		return true;
	}
}
