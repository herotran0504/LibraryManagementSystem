package librarysystem.controller;

import javafx.fxml.FXMLLoader;
import librarysystem.main.Main;
import librarysystem.main.MainController;

import java.io.IOException;

public class UiLoader {

    private static MainController mainController;

    public static void setMainController(MainController controller) {
        System.out.println("setMainController(" + controller + ")");
        mainController = controller;
    }

    public static void loadUI(String fxml, Object userData) {
        try {
            mainController.setUi(FXMLLoader.load(Main.class.getResource(fxml)), userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUI(String fxml) {
        loadUI(fxml, null);
    }
}