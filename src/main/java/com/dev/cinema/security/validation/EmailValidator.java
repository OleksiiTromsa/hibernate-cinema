package com.dev.cinema.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    static final String EMAIL_VALIDATION_REGEX = ".+@.+\\..+";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_VALIDATION_REGEX);
    }
}
