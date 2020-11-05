package com.dev.cinema.security.validation;

import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto userDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword() != null && userDto.getRepeatPassword() != null
                && userDto.getPassword().equals(userDto.getRepeatPassword());
    }
}
