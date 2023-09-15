package librarysystem.main;

import book.init.BookInitializer;
import core.navigator.GlobalProvider;
import core.service.Initializer;
import librarysystem.controller.ViewControllerLoaderImpl;
import librarysystem.navigator.NavigatorImpl;
import login.init.LoginInitializer;

import java.util.Arrays;
import java.util.List;

final class AppInitializer implements Initializer {
    private AppInitializer() {
    }

    @Override
    public void initialize() {
        List<Initializer> boostraps = Arrays.asList(
                new LoginInitializer(),
                new BookInitializer()
        );
        boostraps.forEach(Initializer::initialize);
        GlobalProvider.getInstance().navigator = new NavigatorImpl();
        GlobalProvider.getInstance().appClass = App.class;
        GlobalProvider.getInstance().loader = new ViewControllerLoaderImpl();
    }

    static void start() {
        new AppInitializer().initialize();
    }
}
