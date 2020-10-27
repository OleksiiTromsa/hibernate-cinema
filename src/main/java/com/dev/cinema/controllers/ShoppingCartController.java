package com.dev.cinema.controllers;

import com.dev.cinema.mappers.ShoppingCartMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final MovieSessionService sessionService;
    private final ShoppingCartMapper cartMapper;

    public ShoppingCartController(ShoppingCartService cartService,
                                  UserService userService,
                                  MovieSessionService sessionService,
                                  ShoppingCartMapper cartMapper) {
        this.cartService = cartService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.cartMapper = cartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        ShoppingCart cart = cartService.getByUser(userService.getById(userId));
        return cartMapper.cartToDto(cart);
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long sessionId, @RequestParam Long userId) {
        MovieSession session = sessionService.getById(sessionId);
        User user = userService.getById(userId);
        cartService.addSession(session, user);
    }
}
