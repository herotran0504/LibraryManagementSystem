package librarysystem.user.controller;

import business.User;
import dataaccess.UserDao;
import librarysystem.utils.Result;

class UserControllerImpl implements UserController {
    private final UserDao userDao;

    UserControllerImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result addUser(User user) {
        try {
            userDao.addUser(user);
            return new Result(true, "Successfully added");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    @Override
    public Result checkUser(User user) {
        try {
            User result = userDao.checkUser(user);
            if (result != null) {
                UserData.auth = result.getAuthorization();
                return new Result(true, "User exist");
            } else {
                UserData.auth = null;
                return new Result(false, "User doesn't exist");
            }
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }
}
