package librarysystem.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static librarysystem.util.Const.VIEW_LOGIN;
import static librarysystem.util.Const.TITLE_LIBRARY_MANAGEMENT_SYSTEM;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(TITLE_LIBRARY_MANAGEMENT_SYSTEM);
        stage.setScene(createScene(loadUserLogin()));
        stage.setResizable(false);
        Platform.setImplicitExit(false);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    private static Pane loadUserLogin() throws IOException {
        return FXMLLoader.load(Main.class.getResource(VIEW_LOGIN));
    }

    private static Scene createScene(Pane mainPane) {
        return new Scene(mainPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}