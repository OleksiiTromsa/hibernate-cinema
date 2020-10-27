package com.dev.cinema.controllers;

import com.dev.cinema.mappers.UserMapper;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return service.findByEmail(email)
                .map(mapper::userToDto)
                .orElseThrow();
    }
}
