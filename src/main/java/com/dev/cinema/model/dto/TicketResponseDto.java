package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long id;
    private Long movieSessionId;
    private String movieTitle;
    private Long cinemaHallId;
    private String showTime;
}
