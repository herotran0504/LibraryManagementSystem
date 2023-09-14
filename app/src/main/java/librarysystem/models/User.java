package librarysystem.models;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 8093511372121801726L;

    private final String userName;
    private final String userPassword;
    private Role role;

    public User(String userName, String userPassword, Role role) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Role getRole() {
        return role;
    }

}
