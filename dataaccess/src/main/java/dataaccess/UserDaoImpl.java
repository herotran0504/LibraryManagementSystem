package dataaccess;

import business.User;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;
import librarysystem.utils.Result;

import java.util.HashMap;
import java.util.Map.Entry;

public class UserDaoImpl implements UserDao {
    private static HashMap<String, User> users;

    @Override
    public void addUser(User user) throws Result {
        HashMap<String, User> mems = readUserMap();
        if (!mems.containsKey(user.getId())) {
            mems.put(user.getId(), user);
            users = mems;
            FileOperation.saveToStorage(StorageType.USERS, mems);
            users.put(user.getId(), user);
        } else {
            System.out.println("ERROR: user already exist");
        }


    }

    @Override
    public User checkUser(User user) throws Result {
        HashMap<String, User> mems = readUserMap();
        for (Entry<String, User> entry : mems.entrySet()) {
            User temp = entry.getValue();
            if (user.getId().equals(temp.getId())
                    && user.getPassword().equals(temp.getPassword()))
                return temp;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() throws Result {
        if (users == null) {
            try {
                if (FileOperation.readFromStorage(StorageType.USERS) != null)
                    users = (HashMap<String, User>) FileOperation
                            .readFromStorage(StorageType.USERS);
                else
                    return users = new HashMap<>();

            } catch (Exception e) {
                e.printStackTrace();
                throw new Result(false, e.getMessage());

            }
        }
        return users;
    }

}