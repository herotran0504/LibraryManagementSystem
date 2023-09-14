package dataaccess;

import business.User;
import librarysystem.utils.Result;

public interface UserDao {
    void addUser(User user) throws Result;

    User checkUser(User user) throws Result;
}
