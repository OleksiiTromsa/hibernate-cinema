package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @NotNull(message = "Movie id should not be null")
    private Long movieId;
    @NotNull(message = "Cinema hall id should not be null")
    private Long cinemaHallId;
    @NotNull(message = "Show time should not be null")
    private String showTime;
}
