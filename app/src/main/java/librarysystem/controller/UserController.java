package librarysystem.controller;

import librarysystem.dao.UserDao;
import librarysystem.models.Auth;
import librarysystem.models.User;
import librarysystem.util.Result;

public class UserController {
    public static Auth auth;
    private final UserDao userDao;

    UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public Result addUser(User user) {
        try {
            userDao.addUser(user);
            return new Result(true, "Successfully added");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public Result checkUser(User user) {
        try {
            User result = userDao.checkUser(user);
            if (result != null) {
                auth = result.getAuthorization();
                return new Result(true, "User exist");
            } else {
                auth = null;
                return new Result(false, "User doesn't exist");
            }
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }
}
