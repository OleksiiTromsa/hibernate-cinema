package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(System.out::println);
        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie matrix = new Movie("Matrix");
        fastAndFurious = movieService.add(fastAndFurious);
        matrix = movieService.add(matrix);

        movieService.getAll().forEach(System.out::println);

        CinemaHall imax = new CinemaHall(100, "Imax");
        CinemaHall vip = new CinemaHall(10, "Vip");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        imax = cinemaHallService.add(imax);
        vip = cinemaHallService.add(vip);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

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
        movieSessions.forEach(System.out::println);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        User bob = new User();
        try {
            authenticationService.register("bob@gmail.com", "1");
            bob = authenticationService.login("bob@gmail.com", "1");
            System.out.println(bob);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        ShoppingCartService cartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        cartService.addSession(matrixInImaxToday, bob);
        ShoppingCart cart = cartService.getByUser(bob);
        System.out.println(cart);
    }
}
