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
        stackPane.requestLayout();
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Force a resize of the pane
            stackPane.requestLayout();
        }).start();
    }

}