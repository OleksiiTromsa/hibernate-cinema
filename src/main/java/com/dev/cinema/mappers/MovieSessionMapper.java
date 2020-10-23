package com.dev.cinema.mappers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession mapMovieSessionRequestDtoToMovieSession(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(dto.getShowTime()));
        return movieSession;
    }

    public MovieSessionResponseDto mapMovieSessionToMovieSessionResponseDto(
            MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setMovieSessionId(movieSession.getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setShowTime(movieSession.getShowTime().toString());
        return dto;
    }
}
