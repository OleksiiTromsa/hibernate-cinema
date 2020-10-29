package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import java.util.List;
import java.util.Optional;

public interface CinemaHallDao extends GenericDao<CinemaHall> {
    List<CinemaHall> getAll();

    Optional<CinemaHall> getById(Long id);
}
