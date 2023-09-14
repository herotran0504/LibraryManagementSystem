module librarysystem.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires librarysystem.utils;

    opens librarysystem.main to javafx.fxml;
    exports librarysystem.main;

    opens librarysystem.controller to javafx.fxml;
    exports librarysystem.controller;

    opens librarysystem.mappings to javafx.fxml;
    exports librarysystem.mappings;

    opens librarysystem.util to javafx.fxml;
    exports librarysystem.util;

    opens librarysystem.models to javafx.fxml;
    exports librarysystem.models;
}