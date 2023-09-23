package librarysystem.main;

import book.init.BookInitializer;
import checkout.init.CheckoutInitializer;
import core.navigator.GlobalProvider;
import core.service.Initializer;
import librarysystem.loader.ViewControllerLoaderImpl;
import librarysystem.navigator.NavigatorImpl;
import login.init.LoginInitializer;
import member.init.MemberInitializer;
import mock.TestData;

import java.util.Arrays;

final class AppInitializer implements Initializer {
    private AppInitializer() {
    }

    @Override
    public void initialize() {
        TestData.createDummyData();
        Arrays.asList(
                new LoginInitializer(),
                new BookInitializer(),
                new MemberInitializer(),
                new CheckoutInitializer()
        ).forEach(Initializer::initialize);
        initGlobal();
    }

    private static void initGlobal() {
        GlobalProvider.getInstance().navigator = new NavigatorImpl();
        GlobalProvider.getInstance().appClass = App.class;
        GlobalProvider.getInstance().loader = new ViewControllerLoaderImpl();
    }

    static void start() {
        new AppInitializer().initialize();
    }

}
