package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        MovieService movieService = context.getBean(MovieService.class);

        movieService.getAll().forEach(logger::info);
        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie matrix = new Movie("Matrix");
        fastAndFurious = movieService.add(fastAndFurious);
        matrix = movieService.add(matrix);

        movieService.getAll().forEach(logger::info);

        CinemaHall imax = new CinemaHall(100, "Imax");
        CinemaHall vip = new CinemaHall(10, "Vip");

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        imax = cinemaHallService.add(imax);
        vip = cinemaHallService.add(vip);
        cinemaHallService.getAll().forEach(logger::info);

        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);

        LocalDate dateToday = LocalDate.of(2020, 10, 7);
        LocalDate dateTomorrow = LocalDate.of(2020, 10, 8);
        MovieSession matrixInImaxToday = new MovieSession(matrix, imax,
                LocalDateTime.of(dateToday, LocalTime.of(12, 0)));
        MovieSession matrixInImaxTomorrow = new MovieSession(matrix, imax,
                LocalDateTime.of(dateTomorrow, LocalTime.of(12, 0)));
        MovieSession matrixInVipToday = new MovieSession(matrix, vip,
                LocalDateTime.of(dateToday, LocalTime.of(3, 1)));

        movieSessionService.add(matrixInImaxToday);
        movieSessionService.add(matrixInImaxTomorrow);
        movieSessionService.add(matrixInVipToday);

        List<MovieSession> movieSessions = movieSessionService.findAvailableSessions(matrix.getId(),
                LocalDate.of(2020, 10, 7));
        movieSessions.forEach(logger::info);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);

        User bob = new User();
        try {
            authenticationService.register("bob@gmail.com", "1");
            bob = authenticationService.login("bob@gmail.com", "1");
        } catch (AuthenticationException e) {
            logger.info(e.getMessage());
        }

        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        cartService.addSession(matrixInImaxToday, bob);
        ShoppingCart cart = cartService.getByUser(bob);
        logger.info(cart);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(cart.getTickets(), bob);
        logger.info(orderService.getOrderHistory(bob));
        logger.info(cartService.getByUser(bob));
    }
}
