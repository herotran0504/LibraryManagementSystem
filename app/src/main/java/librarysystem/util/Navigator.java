package librarysystem.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import librarysystem.controller.UiLoader;
import librarysystem.main.Main;
import librarysystem.main.MainController;

import java.io.IOException;

import static librarysystem.util.Const.*;

public abstract class Navigator {

    public static void openDashboardView() {
        try {
            Stage stage = new Stage();
            stage.setTitle(TITLE_LIBRARY_MANAGEMENT_SYSTEM);
            stage.setResizable(false);
            Pane mainPane = loadMainPane();
            stage.setScene(new Scene(mainPane));
            Platform.setImplicitExit(false);
            stage.show();
            stage.setOnCloseRequest(event -> Platform.exit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openNewMemberView() {
        try {
            UiLoader.loadUI(Const.VIEW_MEMBER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMembersListView() {
        try {
            UiLoader.loadUI(Const.VIEW_MEMBER_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openNewBookView() {
        try {
            UiLoader.loadUI(Const.VIEW_BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCheckoutView() {
        try {
            UiLoader.loadUI(Const.VIEW_CHECKOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAddCopyView() {
        try {
            UiLoader.loadUI(Const.VIEW_ADD_COPY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverdueCopiesView() {
        try {
            UiLoader.loadUI(VIEW_OVERDUE_COPIES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(VIEW_MAIN));
        Pane mainPane = loader.load();
        MainController mainController = loader.getController();
        UiLoader.setMainController(mainController);
        UiLoader.loadUI(VIEW_DASHBOARD);
        return mainPane;
    }

}
