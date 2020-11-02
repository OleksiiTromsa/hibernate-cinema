package com.dev.cinema.controllers;

import com.dev.cinema.mappers.OrderMapper;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final UserService userService;
    private final ShoppingCartService cartService;

    public OrderController(OrderService orderService, OrderMapper mapper,
                           UserService userService, ShoppingCartService cartService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public List<OrderResponseDto> getUserHistory(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return orderService.getOrderHistory(userService.findByEmail(email))
                .stream()
                .map(mapper::orderToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        ShoppingCart cart = cartService.getByUser(userService.findByEmail(email));
        orderService.completeOrder(cart.getTickets(), cart.getUser());
    }
}
