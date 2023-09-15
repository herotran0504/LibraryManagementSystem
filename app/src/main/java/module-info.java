module librarysystem.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires librarysystem.utils;
    requires business;
    requires dataaccess;
    requires login;
    requires core;
    requires book;
    requires member;
    requires checkout;

    opens librarysystem.main to javafx.fxml;
    exports librarysystem.main;

    opens librarysystem.util to javafx.fxml;
    exports librarysystem.util;

    exports mock;
    opens mock to javafx.fxml;

    exports librarysystem.dashboard;
    opens librarysystem.dashboard to javafx.fxml;

    exports librarysystem.loader;
    opens librarysystem.loader to javafx.fxml;
}