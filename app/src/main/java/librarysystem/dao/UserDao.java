package librarysystem.dao;

import librarysystem.models.User;
import librarysystem.util.Result;

public interface UserDao {
    void addUser(User user) throws Result;

    User checkUser(User user) throws Result;
}
