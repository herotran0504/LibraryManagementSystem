package core.viewmodel;

import business.User;
import business.exception.LoginException;
import librarysystem.utils.Result;

public interface UserViewModel extends ViewModel {
    Result<Void> addUser(User user) throws LoginException;

    Result<User> checkUser(User user) throws LoginException;

    boolean validate(String userId, String pwd);
}
