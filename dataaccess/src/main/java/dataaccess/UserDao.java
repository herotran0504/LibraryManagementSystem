package dataaccess;

import business.User;
import business.exception.UserException;

public interface UserDao {
    void addUser(User user) throws UserException;

    User checkUser(User user) throws UserException;
}
