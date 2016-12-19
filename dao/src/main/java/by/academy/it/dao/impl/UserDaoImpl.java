package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public String getPassword(String login) throws DaoException {
        String hql = "SELECT U.password FROM User U WHERE U.login=:login";
        String pass;
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            pass = (String) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return pass;
    }

    public User getUserByLogin(String login) throws DaoException {
        String hql = "SELECT U FROM User U WHERE U.login=:login";
        User user;
        try {
            Query query = getSession().createQuery(hql);
            LOG.info("Requested login: " + login);
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return user;
    }

    public int getUserId(String login) throws DaoException {
        String hql = "FROM User WHERE login= :login";
        Integer userId = null;
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            userId = user.getId();
        } catch (HibernateException e) {
            LOG.error("Unable to login. Error in DAO. " + e);
        }
        return userId;
    }
}

