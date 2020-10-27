package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public void register(@RequestBody UserRequestDto dto) {
        service.register(dto.getEmail(), dto.getPassword());
    }
}
