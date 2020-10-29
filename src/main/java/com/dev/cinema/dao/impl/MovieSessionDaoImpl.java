package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession>
        implements MovieSessionDao {
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MovieSession ms "
                            + "JOIN FETCH ms.movie "
                            + "JOIN FETCH ms.cinemaHall "
                            + "WHERE ms.movie.id = :movieId "
                            + "AND ms.showTime BETWEEN :startTime AND :endTime",
                    MovieSession.class)
            .setParameter("movieId", movieId)
            .setParameter("startTime", date.atTime(LocalTime.MIN))
            .setParameter("endTime", date.atTime(LocalTime.MAX))
            .getResultList();
        }
    }

    @Override
    public Optional<MovieSession> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(MovieSession.class, id));
        }
    }
}
