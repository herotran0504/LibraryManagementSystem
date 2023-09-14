package librarysystem.models;

import java.io.Serializable;

public final class User implements Serializable {

    private final String id;
    private final String password;
    private Auth authorization;

    public User(String id, String password, Auth authorization) {
        this.id = id;
        this.password = password;
        this.authorization = authorization;
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Auth getAuthorization() {
        return authorization;
    }

    @Override
    public String toString() {
        return "[" + id + ":" + password + ", " + authorization.toString() + "]";
    }
}
