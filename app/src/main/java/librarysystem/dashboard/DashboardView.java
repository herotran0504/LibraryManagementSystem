package librarysystem.dashboard;

import business.Auth;
import core.auth.UserAuthData;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import librarysystem.main.App;
import librarysystem.util.Const;

import java.net.URL;
import java.util.ResourceBundle;

import static librarysystem.util.Const.VIEW_OVERDUE_COPIES;

public class DashboardView implements Initializable {

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
    protected void openMemberView(ActionEvent event) {
        GlobalProvider.getInstance().navigator.openMemberView();
    }

    @FXML
    protected void openMembersTable(ActionEvent event) {
        openMembersListView();
    }

    public void openMembersListView() {
        GlobalProvider.getInstance().navigator.openMemberListView();
    }

    public void openCheckoutView() {
        GlobalProvider.getInstance().navigator.openCheckoutView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserAuthData.getAuth().equals(Auth.ADMIN)) {
            checkout.setDisable(true);
        } else if (UserAuthData.getAuth().equals(Auth.LIBRARIAN)) {
            addCopy.setDisable(true);
            openBook.setDisable(true);
            addMember.setDisable(true);
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
        GlobalProvider.getInstance().loader.loadViewController(Const.VIEW_BOOK);
    }

    @FXML
    protected void addCopy(ActionEvent event) {
        GlobalProvider.getInstance().loader.loadViewController(Const.VIEW_ADD_COPY);
    }

    @FXML
    protected void viewOverdueCopies(ActionEvent event) {
        GlobalProvider.getInstance().loader.loadViewController(VIEW_OVERDUE_COPIES);
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
