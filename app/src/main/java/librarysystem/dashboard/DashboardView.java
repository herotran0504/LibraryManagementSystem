package librarysystem.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import librarysystem.main.Main;
import business.Auth;
import librarysystem.user.controller.UserData;
import librarysystem.util.DialogUtil;
import librarysystem.util.Navigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardView extends Navigator implements Initializable {
    @FXML
    private Button memberView;
    @FXML
    private Button checkout;
    @FXML
    private Button openBook;
    /*@FXML
    private Button openPeriodical;*/
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
    protected void openMemberView(ActionEvent event) throws IOException {
        openNewMemberView();
    }

    @FXML
    protected void openMembersTable(ActionEvent event) throws IOException {
        openMembersListView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserData.auth.toString().equals(Auth.ADMIN.toString())) {
            checkout.setDisable(true);
            welcomeLBL.setText(greeting(Auth.ADMIN));
        } else if (UserData.auth.toString().equals(Auth.LIBRARIAN.toString())) {
            addCopy.setDisable(true);
            openBook.setDisable(true);
            /*openPeriodical.setDisable(true);*/
            addMember.setDisable(true);
            final String value = greeting(Auth.LIBRARIAN);
            welcomeLBL.setText(value);

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
        openNewBookView();
    }

    @FXML
    protected void addCopy(ActionEvent event) {
        openAddCopyView();
    }

    @FXML
    protected void viewOverdueCopies(ActionEvent event) {
        openOverdueCopiesView();
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