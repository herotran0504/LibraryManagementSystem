package librarysystem.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.Alert.AlertType.WARNING;

public class DialogUtil {

    private static final String INFORMATION_TITLE = "Information";
    private static final String WARNING_TITLE = "Warning";
    private static final String EXCEPTION_TITLE = "Error";
    private static final String EXCEPTION_CONFIRMATION = "Confirmation";

    public static void showInformationDialog(String message) {
        Alert alert = new Alert(INFORMATION);
        alert.setTitle(INFORMATION_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showWarningDialog(String message) {
        Alert alert = new Alert(WARNING);
        alert.setTitle(WARNING_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showExceptionDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(EXCEPTION_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Boolean showConfirmDialog(String confirmMsg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(EXCEPTION_CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(confirmMsg);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;

    }

    public static void showServiceResponseMessage(Result serviceResponse) {
        if (serviceResponse.getSuccess()) {
            showInformationDialog(serviceResponse.getMessage());
        } else {
            showExceptionDialog(serviceResponse.getMessage());
        }
    }

    public static void showServiceResponseMessage() {
        showExceptionDialog(Result.getRuntimeException());
    }

}
