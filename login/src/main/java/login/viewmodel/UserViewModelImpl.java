package login.viewmodel;

import business.User;
import business.exception.LoginException;
import core.auth.UserAuthData;
import core.util.DialogUtil;
import core.viewmodel.UserViewModel;
import dataaccess.DaoFactory;
import dataaccess.UserDao;
import librarysystem.utils.Result;

public final class UserViewModelImpl implements UserViewModel {
    private final UserDao userDao;

    public UserViewModelImpl(UserDao userDao) {
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
                UserAuthData.setAuth(result.getAuthorization());
                return new Result<>(true, "User exist");
            } else {
                UserAuthData.clearAuth();
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

    public static UserViewModel create() {
        return new UserViewModelImpl(DaoFactory.getDaoFactory().getUserDao());
    }
}
