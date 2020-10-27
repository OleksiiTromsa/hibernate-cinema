package com.dev.cinema.mappers;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionMapper sessionMapper;

    public TicketMapper(MovieSessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    public TicketResponseDto ticketToDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setMovieSessionDto(sessionMapper.mapMovieSessionToDto(ticket.getMovieSession()));
        return dto;
    }
}
