package core.auth;

import business.Auth;

    public class UserAuthData {
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

    public static UserAuthData getInstance() {
        return UserAuthData.Holder.instance;
    }

    private static class Holder {
        public static UserAuthData instance = new UserAuthData();
    }
}
