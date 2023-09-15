package librarysystem.controller;

import core.viewmodel.AppController;
import javafx.fxml.FXMLLoader;
import librarysystem.main.App;

import java.io.IOException;

public class UiLoader {

    private AppController appController;

    public static void setAppController(AppController controller) {
        getInstance().appController = controller;
    }

    public static void loadUI(String fxml, Object userData) {
        try {
            getInstance().appController.setUi(FXMLLoader.load(App.class.getResource(fxml)), userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUI(String fxml) {
        loadUI(fxml, null);
    }

    private static class Holder {
        public static UiLoader instance = new UiLoader();
    }

    public static UiLoader getInstance() {
        return Holder.instance;
    }
}