package by.academy.it.services;

import by.academy.it.entity.User;

public interface UserService extends Service<User> {
    boolean checkLogin(String enterLogin, String enterPass);

    User getUserByLogin(String login);

    int getUserId(String login);

    String hash(String input);

}
