package librarysystem.navigator;

import core.navigator.Navigator;
import core.viewmodel.AppController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import librarysystem.controller.UiLoader;
import librarysystem.main.App;

import java.io.IOException;

import static librarysystem.util.Const.*;

public class NavigatorImpl implements Navigator {

    @Override
    public void openDashboardView() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(TITLE_LIBRARY_MANAGEMENT_SYSTEM);
        stage.setResizable(false);
        Pane mainPane = loadMainPane();
        stage.setScene(new Scene(mainPane));
        Platform.setImplicitExit(false);
        stage.show();
        stage.setOnCloseRequest(event -> Platform.exit());
    }

    private static Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(VIEW_MAIN));
        Pane mainPane = loader.load();
        AppController appController = loader.getController();
        UiLoader.setAppController(appController);
        UiLoader.loadUI(VIEW_DASHBOARD);
        return mainPane;
    }
}
