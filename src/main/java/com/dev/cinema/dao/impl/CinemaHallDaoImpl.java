package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends GenericDaoImpl<CinemaHall>
        implements CinemaHallDao {
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CinemaHall ", CinemaHall.class).getResultList();
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CinemaHall.class, id);
        }
    }
}
