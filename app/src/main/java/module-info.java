module librarysystem.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires librarysystem.utils;
    requires business;
    requires dataaccess;

    opens librarysystem.main to javafx.fxml;
    exports librarysystem.main;

    opens librarysystem.controller to javafx.fxml;
    exports librarysystem.controller;

    opens librarysystem.mappings to javafx.fxml;
    exports librarysystem.mappings;

    opens librarysystem.util to javafx.fxml;
    exports librarysystem.util;

    exports mock;
    opens mock to javafx.fxml;
}