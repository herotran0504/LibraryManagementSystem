package librarysystem.navigator;

import core.loader.ViewControllerLoader;
import core.navigator.GlobalProvider;
import core.navigator.Navigator;
import core.viewmodel.AppController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import librarysystem.main.App;
import librarysystem.util.Const;

import java.io.IOException;

import static librarysystem.util.Const.*;

public class NavigatorImpl implements Navigator {

    @Override
    public void reloadDashBoardView() {
        loadViewController(VIEW_DASHBOARD);
    }

    @Override
    public void openMemberView() {
        loadViewController(VIEW_MEMBER);
    }

    @Override
    public void openMemberListView() {
        loadViewController(VIEW_MEMBER_TABLE);
    }

    @Override
    public void openCheckoutView() {
        loadViewController(Const.VIEW_CHECKOUT);
    }

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
        final ViewControllerLoader controllerLoader = GlobalProvider.getInstance().loader;
        controllerLoader.attachAppController(appController);
        controllerLoader.loadViewController(VIEW_DASHBOARD);
        return mainPane;
    }

    private static void loadViewController(String viewMember) {
        GlobalProvider.getInstance().loader.loadViewController(viewMember);
    }
}
