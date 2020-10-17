package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDaoImpl<T> implements GenericDao<T> {
    private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

    @Override
    public T add(T item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            logger.info(item.getClass().getSimpleName() + " entity " + item
                    + " successfully added to the DB");
            return item;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert entity " + item, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
