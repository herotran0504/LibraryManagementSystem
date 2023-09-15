module login {
    requires javafx.controls;
    requires javafx.fxml;

    exports login.init;
    requires business;
    requires librarysystem.utils;
    requires core;
    requires dataaccess;

    exports login.view;
    opens login.view to javafx.fxml;
}
