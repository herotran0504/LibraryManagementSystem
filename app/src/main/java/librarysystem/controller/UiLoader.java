package librarysystem.controller;

import javafx.fxml.FXMLLoader;
import librarysystem.main.Main;
import librarysystem.main.MainController;

import java.io.IOException;

public class UiLoader {

    private MainController mainController;

    public static void setMainController(MainController controller) {
        getInstance().mainController = controller;
    }

    public static void loadUI(String fxml, Object userData) {
        try {
            getInstance().mainController.setUi(FXMLLoader.load(Main.class.getResource(fxml)), userData);
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