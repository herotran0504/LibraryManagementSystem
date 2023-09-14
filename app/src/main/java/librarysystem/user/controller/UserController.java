package librarysystem.user.controller;

import business.User;
import dataaccess.UserDao;
import librarysystem.utils.Result;

public interface UserController {

    Result addUser(User user);

    Result checkUser(User user);

    static UserController get(UserDao userDao) {
        return new UserControllerImpl(userDao);
    }
}
