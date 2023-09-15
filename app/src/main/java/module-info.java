module librarysystem.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires librarysystem.utils;
    requires business;
    requires dataaccess;
    requires login;
    requires core;

    opens librarysystem.main to javafx.fxml;
    exports librarysystem.main;

    opens librarysystem.controller to javafx.fxml;
    exports librarysystem.controller;

    opens librarysystem.util to javafx.fxml;
    exports librarysystem.util;

    exports mock;
    opens mock to javafx.fxml;
    exports librarysystem.member.view;
    opens librarysystem.member.view to javafx.fxml;

    exports librarysystem.member.controller;
    opens librarysystem.member.controller to javafx.fxml;

    exports librarysystem.book.controller;
    opens librarysystem.book.controller to javafx.fxml;

    exports librarysystem.dashboard;
    opens librarysystem.dashboard to javafx.fxml;

    exports librarysystem.book.view;
    opens librarysystem.book.view to javafx.fxml;
}