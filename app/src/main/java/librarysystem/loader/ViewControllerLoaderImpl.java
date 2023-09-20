package librarysystem.loader;

import core.loader.ViewControllerLoader;
import core.viewmodel.AppController;
import javafx.fxml.FXMLLoader;
import librarysystem.main.App;
import librarysystem.utils.SimpleLogger;

import java.io.IOException;

public final class ViewControllerLoaderImpl implements ViewControllerLoader {

    private AppController appController;

    @Override
    public void attachAppController(AppController controller) {
        appController = controller;
    }

    @Override
    public void loadViewController(String fxml, Object userData) {
        try {
            appController.setUi(FXMLLoader.load(App.class.getResource(fxml)), userData);
        } catch (IOException e) {
            SimpleLogger.logError(e);
        }
    }

    @Override
    public void loadViewController(String fxml) {
        loadViewController(fxml, null);
    }


}