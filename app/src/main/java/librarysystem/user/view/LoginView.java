package librarysystem.user.view;

import business.User;
import business.exception.LoginException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import librarysystem.controller.ControllerFactory;
import librarysystem.user.controller.UserController;
import librarysystem.util.DialogUtil;
import librarysystem.util.Navigator;
import librarysystem.utils.Result;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView extends Navigator implements Initializable {

    @FXML
    private TextField userId;
    @FXML
    private PasswordField userPwd;

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
                User user = new User(getUserId(), getUserPwd());
                Result<User> result = controller.checkUser(user);
                if (result.getSuccess()) {
                    userPwd.getScene().getWindow().hide();
                    openDashboardView();
                } else {
                    showLoginError();
                }
            }
        } catch (LoginException e) {
            showLoginError();
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    private static void showLoginError() {
        DialogUtil.showWarningDialog("Login couldn't be completed. Please be sure to enter correct username and password.");
    }

    private String getUserId() {
        return userId.textProperty().get();
    }

    private String getUserPwd() {
        return userPwd.textProperty().get();
    }

    private boolean validateForm() {
        if (getUserId().isEmpty() || getUserPwd().isEmpty()) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }
        return true;
    }

}
