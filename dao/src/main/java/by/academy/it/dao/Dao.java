package by.academy.it.dao;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.AbstractEntity;

public interface Dao<T extends AbstractEntity> {
    void create(T entity) throws DaoException;

    T findEntityById(Integer id) throws DaoException;

    void delete(Integer id) throws DaoException;

}
