package librarysystem.dashboard;

import core.util.DialogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import librarysystem.controller.UiLoader;
import librarysystem.main.App;
import librarysystem.util.Const;

import java.net.URL;
import java.util.ResourceBundle;

import static librarysystem.util.Const.VIEW_OVERDUE_COPIES;

public class DashboardView implements Initializable {
    @FXML
    private Button logout;

    @FXML
    protected void openMemberView(ActionEvent event) {
        UiLoader.loadUI(Const.VIEW_MEMBER);
    }

    @FXML
    protected void openMembersTable(ActionEvent event) {
        openMembersListView();
    }

    public void openMembersListView() {
        UiLoader.loadUI(Const.VIEW_MEMBER_TABLE);
    }

    public void openCheckoutView() {
        UiLoader.loadUI(Const.VIEW_CHECKOUT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    protected void openCheckout(ActionEvent event) {
        openCheckoutView();
    }

    @FXML
    protected void openBook(ActionEvent event) {
        UiLoader.loadUI(Const.VIEW_BOOK);
    }

    @FXML
    protected void addCopy(ActionEvent event) {
        UiLoader.loadUI(Const.VIEW_ADD_COPY);
    }

    @FXML
    protected void viewOverdueCopies(ActionEvent event) {
        UiLoader.loadUI(VIEW_OVERDUE_COPIES);
    }

    @FXML
    protected void logout(ActionEvent event) {
        if (DialogUtil.showConfirmDialog("Are you sure to logout?")) {
            try {
                logout.getScene().getWindow().hide();
                App app = new App();
                Stage stage = new Stage();
                app.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
