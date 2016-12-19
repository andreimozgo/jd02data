package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class UserDaoImplTest extends Assert {

    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate() throws DaoException {
        User user = new User();
        user.setId(1);
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        User readUser = userDao.findEntityById(1);
        assertNotNull(readUser);
        assertEquals(user, readUser);
    }

    @Test
    public void testDelete() throws DaoException {
        User user = new User();
        user.setId(1);
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        userDao.delete(1);
        User deletedUser = userDao.findEntityById(1);
        assertNull(deletedUser);
    }

    @Test
    public void testFindEntityById() throws DaoException {
        User user = new User();
        user.setId(1);
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        User readUser = userDao.findEntityById(1);
        assertEquals(user, readUser);
    }

    @Test
    public void testGetPassword() throws DaoException {
        User user = new User();
        user.setId(1);
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        String password = user.getPassword();
        assertEquals("testPassword", password);
    }

    @Test
    public void testGetUserByLogin() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        User gotUser = userDao.getUserByLogin("testUser");
        assertEquals(user, gotUser);
    }

    @Test
    public void testGetUserId() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userDao.create(user);
        Integer gotUserId = userDao.getUserId("testUser");
        assertEquals(user.getId(), gotUserId);
    }
}
