package librarysystem.mappings;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UserController;
import librarysystem.models.User;
import librarysystem.util.Navigator;
import librarysystem.util.DialogUtil;
import librarysystem.util.Result;

import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginMapper extends Navigator implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;

    private final UserController controller = ControllerFactory.get().getUserController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    protected void doCancel() {
        if (DialogUtil.showConfirmDialog("Are you want to exit?")) {
            Platform.exit();
        }
    }

    @FXML
    protected void doLogin() {
        try {
            if (validateForm()) {
                User user = new User(getUserName(), getUserPassword());
                Result result = controller.checkUser(user);
                if (result.getSuccess()) {
                    userPassword.getScene().getWindow().hide();
                    openDashboardView();
                } else {
                    DialogUtil.showWarningDialog("Login couldn't be completed. Please be sure to enter correct username and password.");
                }
            }
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage();
        }
    }

    private String getUserName() {
        return userName.textProperty().get();
    }

    private String getUserPassword() {
        return userPassword.textProperty().get();
    }

    private boolean validateForm() {
        if (getUserName().isEmpty() || getUserPassword().isEmpty()) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }
        return true;
    }

}
