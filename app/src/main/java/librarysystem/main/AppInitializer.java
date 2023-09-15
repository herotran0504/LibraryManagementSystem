package librarysystem.main;

import core.navigator.Navigator;
import core.navigator.GlobalProvider;
import core.service.Initializer;
import librarysystem.navigator.NavigatorImpl;
import login.init.LoginInitializer;
import mock.Main;

import java.util.Arrays;
import java.util.List;

final class AppInitializer implements Initializer {
    private AppInitializer() {
    }

    @Override
    public void initialize() {
        List<Initializer> boostraps = Arrays.asList(
                new LoginInitializer()
        );
        boostraps.forEach(Initializer::initialize);
        Navigator navigator = new NavigatorImpl();
        GlobalProvider.getInstance().navigator = navigator;
        GlobalProvider.getInstance().appClass = App.class;
    }

    static void start() {
        new AppInitializer().initialize();
    }
}
