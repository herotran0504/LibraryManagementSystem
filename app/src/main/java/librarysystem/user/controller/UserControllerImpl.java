package librarysystem.user.controller;

import business.User;
import business.exception.LoginException;
import dataaccess.UserDao;
import librarysystem.util.DialogUtil;
import librarysystem.utils.Result;

class UserControllerImpl implements UserController {
    private final UserDao userDao;

    UserControllerImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result<Void> addUser(User user) throws LoginException {
        try {
            userDao.addUser(user);
            return new Result<>(true, "Successfully added");
        } catch (Exception e) {
            throw new LoginException(e);
        }
    }

    @Override
    public Result<User> checkUser(User user) throws LoginException {
        try {
            User result = userDao.checkUser(user);
            if (result != null) {
                UserData.setAuth(result.getAuthorization());
                return new Result<>(true, "User exist");
            } else {
                UserData.clearAuth();
                return new Result<>(false, "User doesn't exist");
            }
        } catch (Exception e) {
            throw new LoginException(e);
        }
    }

    @Override
    public boolean validate(String userId, String pwd) {
        if (userId.trim().isEmpty() || pwd.trim().isEmpty()) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }
        return true;
    }

}
