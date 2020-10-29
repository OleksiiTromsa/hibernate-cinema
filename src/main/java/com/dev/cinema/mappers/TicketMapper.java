package com.dev.cinema.mappers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto ticketToDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        MovieSession session = ticket.getMovieSession();
        dto.setMovieSessionId(session.getId());
        dto.setCinemaHallId(session.getCinemaHall().getId());
        dto.setMovieTitle(session.getMovie().getTitle());
        dto.setShowTime(session.getShowTime().toString());
        return dto;
    }
}
