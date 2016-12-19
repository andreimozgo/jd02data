package by.academy.it.dao.impl;

import by.academy.it.dao.Dao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;

@Repository
public class BaseDao<T extends AbstractEntity> implements Dao<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void create(T t) throws DaoException {
        try {
            getSession().saveOrUpdate(t);
            log.info("Created or updated: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    public T findEntityById(Integer id) throws DaoException {
        T t;
        try {
            log.info("Get Entity by id:" + id);
            t = (T) getSession().get(getPersistentClass(), id);
            log.info("Got entity: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(Integer id) throws DaoException {
        try {
            T t = (T) getSession().get(getPersistentClass(), id);
            getSession().delete(t);
            log.info("Deleted: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
