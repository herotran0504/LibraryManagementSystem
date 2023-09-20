package core.navigator;

import core.loader.ViewControllerLoader;
import javafx.application.Application;

public final class GlobalProvider {

    public Navigator navigator;
    // TODO make loader apart of navigator
    public ViewControllerLoader loader;
    public Class<? extends Application> appClass;

    private GlobalProvider() {
    }

    public static GlobalProvider getInstance() {
        return Holder.INSTANCE;
    }

    static final class Holder {
        static GlobalProvider INSTANCE = new GlobalProvider();

        private Holder() {
        }
    }

}
