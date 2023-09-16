package dataaccess;

import business.User;
import business.exception.LoginException;
import business.exception.UserException;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;

import java.util.Map;
import java.util.Map.Entry;

public class UserDaoImpl implements UserDao {
    private static Map<String, User> users;

    @Override
    public void addUser(User user) throws UserException {
        Map<String, User> mems = readUserMap();
        if (!mems.containsKey(user.getId())) {
            mems.put(user.getId(), user);
            users = mems;
            saveUserMap(mems);
            users.put(user.getId(), user);
        } else {
            System.out.println("ERROR: user already exist");
        }
    }

    private static void saveUserMap(Map<String, User> userMap) {
        FileOperation.saveToStorage(StorageType.USERS, userMap);
    }

    @Override
    public User checkUser(User user) throws UserException {
        Map<String, User> users = readUserMap();
        for (Entry<String, User> entry : users.entrySet()) {
            User temp = entry.getValue();
            if (user.getId().equals(temp.getId()) && user.getPassword().equals(temp.getPassword())) {
                return temp;
            }
        }
        return null;
    }

    public Map<String, User> readUserMap() throws LoginException {
        if (users == null) {
            try {
                return FileOperation.readFromStorageAsMap(StorageType.USERS);
            } catch (Exception e) {
                throw new LoginException(e.getMessage());

            }
        }
        return users;
    }

}
