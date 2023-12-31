module member {
    requires javafx.controls;
    requires javafx.fxml;

    requires core;
    requires dataaccess;
    requires business;
    requires librarysystem.utils;

    exports member.view;
    opens member.view to javafx.fxml;
    exports member.init;
}
