package core.navigator;

import javafx.application.Application;

public final class GlobalProvider {

    public Navigator navigator;
    public Class<? extends Application> appClass;

    private GlobalProvider() {
    }

    public static GlobalProvider getInstance() {
        return Holder.INSTANCE;
    }

    static class Holder {
        static GlobalProvider INSTANCE = new GlobalProvider();
    }

}
