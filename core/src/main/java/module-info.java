module core {
    requires javafx.controls;
    requires javafx.fxml;
    requires librarysystem.utils;
    requires business;

    exports core.navigator;
    opens core.navigator to javafx.fxml;

    exports core.viewmodel;
    opens core.viewmodel to javafx.fxml;

    exports core.util;
    opens core.util to javafx.fxml;

    exports core.service;
    opens core.service to javafx.fxml;
    exports core.auth;
    exports core.loader;
}
