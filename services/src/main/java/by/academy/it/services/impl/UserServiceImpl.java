package by.academy.it.services.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import by.academy.it.services.AbstractService;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkLogin(String enterLogin, String enterPass) {
        boolean passCheckResult = false;
        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }
        try {
            passCheckResult = userDao.getPassword(enterLogin).equals(hash(enterPass));
        } catch (DaoException e) {
            LOG.error("Error check login: ", e);
        }
        return passCheckResult;
    }

    public void createOrUpdate(User user) {
        try {
            String pass = user.getPassword();
            user.setPassword(hash(pass));
            userDao.create(user);
        } catch (DaoException e) {
            LOG.error("Error create or update user: ", e);
        }
    }

    public User findEntityById(Integer id) {
        User user = null;
        try {
            user = userDao.findEntityById(id);
        } catch (DaoException e) {
            LOG.error("Error find ticket: ", e);
        }
        return user;
    }

    public void delete(Integer id) {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            LOG.error("Error delete user: ", e);
        }
    }

    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (DaoException e) {
            LOG.error("Error get user: ", e);
        }
        return user;
    }

    public int getUserId(String login) {
        Integer userId = null;
        try {
            userId = userDao.getUserId(login);
            LOG.info(userId);
        } catch (DaoException e) {
            LOG.error("Error get userId: ", e);
        }
        return userId;
    }

    public String hash(String input) {
        String md5Hashed = null;
        if (null == input) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5Hashed = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error in hash UserService: ", e);
        }
        return md5Hashed;
    }
}
