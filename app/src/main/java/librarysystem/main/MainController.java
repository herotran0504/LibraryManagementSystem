package librarysystem.main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane stackPane;

    public void setUi(Node node, Object userData) {
        stackPane.getChildren().setAll(node);
        stackPane.setUserData(userData);
    }

}