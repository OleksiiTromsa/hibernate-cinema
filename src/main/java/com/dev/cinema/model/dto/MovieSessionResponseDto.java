package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private String movieTitle;
    private Long cinemaHallId;
    private String showTime;
}
