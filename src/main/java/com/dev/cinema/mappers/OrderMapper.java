package com.dev.cinema.mappers;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto orderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticketMapper::ticketToDto)
                .collect(Collectors.toList());
        dto.setTickets(tickets);
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
