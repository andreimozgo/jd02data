package by.academy.it.controllers;

import by.academy.it.entity.User;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    final Logger LOG = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        //gets registration form
        String page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        //gets login page
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        //gets logout page
        LOG.info("User logged out successfully");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }

    @RequestMapping(value = "/addRegistration", method = RequestMethod.POST)
    public String addRegistration(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "password") String password) {

        String page;
        User user = new User(login, password);
        userService.createOrUpdate(user);
        LOG.info("New registration added successfully");

        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage() {
        String page = ConfigurationManager.getProperty("path.page.assessdenied");
        return page;
    }
}
