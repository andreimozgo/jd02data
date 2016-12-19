package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/test-service-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest extends Assert {

    @Autowired
    private UserService userService;

    @Test
    public void testCreate() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userService.createOrUpdate(user);
        User readUser = userService.findEntityById(user.getId());
        assertNotNull(readUser);
        assertEquals(user.getLogin(), readUser.getLogin());
        assertEquals(user.getUserRole(), readUser.getUserRole());
        userService.delete(user.getId());
    }

    @Test
    public void testFindEntityById() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userService.createOrUpdate(user);
        User findUser = userService.findEntityById(user.getId());
        assertNotNull(findUser);
        assertEquals(user.getId(), findUser.getId());
        assertEquals(user.getLogin(), findUser.getLogin());
        assertEquals(user.getUserRole(), findUser.getUserRole());
        userService.delete(user.getId());
    }

    @Test
    public void testDelete() throws DaoException {
        User user = new User();
        user.setId(null);
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userService.createOrUpdate(user);
        userService.delete(user.getId());
        User deletedUser = userService.findEntityById(user.getId());
        assertNull(deletedUser);
    }

    public void getPassword() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setUserRole("user");
        userService.createOrUpdate(user);
        String password = user.getPassword();
        assertEquals(userService.hash("testPassword"), password);
        userService.delete(user.getId());
    }
}
