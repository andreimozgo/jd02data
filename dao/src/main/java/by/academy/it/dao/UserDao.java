package by.academy.it.dao;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;

public interface UserDao extends Dao<User> {

    String getPassword(String login) throws DaoException;

    /**
     * @param login
     * @return
     * @throws DaoException
     */
    User getUserByLogin(String login) throws DaoException;

    int getUserId(String login) throws DaoException;
}
