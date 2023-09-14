package librarysystem.dashboard;

import business.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import librarysystem.controller.UiLoader;
import librarysystem.main.Main;
import librarysystem.user.controller.UserData;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;
import librarysystem.util.Navigator;

import java.net.URL;
import java.util.ResourceBundle;

import static librarysystem.util.Const.VIEW_OVERDUE_COPIES;

public class DashboardView extends Navigator implements Initializable {
    @FXML
    private Button memberView;
    @FXML
    private Button checkout;
    @FXML
    private Button openBook;
    @FXML
    private Button addMember;
    @FXML
    private Button addCopy;
    @FXML
    private Button overdueCopy;
    @FXML
    private Button logout;
    @FXML
    private Label welcomeLBL;

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
        if (UserData.getAuth().equals(Auth.ADMIN)) {
            checkout.setDisable(true);
            welcomeLBL.setText(greeting(Auth.ADMIN));
        } else if (UserData.getAuth().equals(Auth.LIBRARIAN)) {
            addCopy.setDisable(true);
            openBook.setDisable(true);
            addMember.setDisable(true);
            welcomeLBL.setText(greeting(Auth.LIBRARIAN));

        } else {
            welcomeLBL.setText(greeting(Auth.BOTH));
        }
    }

    private static String greeting(Auth librarian) {
        return "Welcome, Access Level [" + librarian + ']';
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
                Main main = new Main();
                Stage stage = new Stage();
                main.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
