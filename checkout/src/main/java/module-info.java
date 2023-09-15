module checkout {
    requires javafx.controls;
    requires javafx.fxml;

    requires core;
    requires dataaccess;
    requires business;
    requires librarysystem.utils;
    requires book;

    exports checkout.view;
    opens checkout.view to javafx.fxml;
    exports checkout.init;
}
