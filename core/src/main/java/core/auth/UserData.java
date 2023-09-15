package core.auth;

import business.Auth;

public class UserData {
    private Auth auth;

    public static void setAuth(Auth auth) {
        getInstance().auth = auth;
    }

    public static void clearAuth() {
        getInstance().auth = null;
    }

    public static Auth getAuth() {
        return getInstance().auth;
    }

    public static UserData getInstance() {
        return UserData.Holder.instance;
    }

    private static class Holder {
        public static UserData instance = new UserData();
    }
}
