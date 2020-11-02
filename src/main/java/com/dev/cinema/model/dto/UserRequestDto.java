package com.dev.cinema.model.dto;

import com.dev.cinema.validation.EmailValidation;
import com.dev.cinema.validation.PasswordValidation;
import lombok.Data;

@Data
@PasswordValidation
public class UserRequestDto {
    @EmailValidation
    private String email;
    private String password;
    private String repeatPassword;
}
