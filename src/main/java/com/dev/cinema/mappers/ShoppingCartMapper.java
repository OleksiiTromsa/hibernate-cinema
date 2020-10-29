package com.dev.cinema.mappers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto cartToDto(ShoppingCart cart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(cart.getId());
        List<TicketResponseDto> tickets = cart.getTickets().stream()
                .map(ticketMapper::ticketToDto)
                .collect(Collectors.toList());
        dto.setTickets(tickets);
        return dto;
    }
}
