package com.dev.cinema.controllers;

import com.dev.cinema.mappers.MovieSessionMapper;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService service;
    private final MovieSessionMapper mapper;

    public MovieSessionController(MovieSessionService service, MovieSessionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto dto) {
        service.add(mapper.mapMovieSessionRequestDtoToMovieSession(dto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getMovieSessions(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return service.findAvailableSessions(movieId, date).stream()
                .map(mapper::mapMovieSessionToMovieSessionResponseDto)
                .collect(Collectors.toList());
    }
}
