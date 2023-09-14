package librarysystem.user.controller;

import business.User;
import business.exception.LoginException;
import dataaccess.UserDao;
import librarysystem.utils.Result;

public interface UserController {

    Result<Void> addUser(User user) throws LoginException;

    Result<User> checkUser(User user) throws LoginException;

    static UserController get(UserDao userDao) {
        return new UserControllerImpl(userDao);
    }
}
