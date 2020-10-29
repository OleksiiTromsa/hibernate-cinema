package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao extends GenericDao<Movie> {
    List<Movie> getAll();

    Optional<Movie> getById(Long id);
}
