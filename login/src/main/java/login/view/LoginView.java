package login.view;

import business.User;
import business.exception.LoginException;
import core.navigator.Navigator;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import core.viewmodel.UserViewModel;
import core.viewmodel.ViewModelRegistry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import librarysystem.utils.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {

    @FXML
    private TextField userId;
    @FXML
    private PasswordField userPwd;

    private final UserViewModel viewModel = ViewModelRegistry.getInstance().get(UserViewModel.class);
    private final Navigator navigator = GlobalProvider.getInstance().navigator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    protected void doLogin() {
        try {
            final String id = getUserId();
            final String pwd = getUserPwd();
            if (viewModel.validate(id, pwd)) {
                User user = new User(id, pwd);
                Result<User> result = viewModel.checkUser(user);
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

    private void openDashboardView() throws IOException {
        navigator.openDashboardView();
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

}
