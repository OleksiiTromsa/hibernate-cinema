package com.dev.cinema.controllers;

import com.dev.cinema.mappers.CinemaHallMapper;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService service;
    private final CinemaHallMapper mapper;

    public CinemaHallController(CinemaHallService service, CinemaHallMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return service.getAll().stream()
                .map(mapper::cinemaHallToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto dto) {
        service.add(mapper.dtoToCinemaHall(dto));
    }
}
