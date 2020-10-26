package com.dev.cinema.controllers;

import com.dev.cinema.mappers.MovieMapper;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;
    private final MovieMapper mapper;

    public MovieController(MovieService service, MovieMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieDto) {
        Movie movie = mapper.mapDtoToMovie(movieDto);
        service.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return service.getAll().stream()
                .map(mapper::mapMovieToDto)
                .collect(Collectors.toList());
    }
}
