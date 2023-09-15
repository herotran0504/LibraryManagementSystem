module book {
    requires javafx.controls;
    requires javafx.fxml;

    requires business;
    requires librarysystem.utils;
    requires core;
    requires dataaccess;

    exports book.view;
    opens book.view to javafx.fxml;
    exports book.init;
}
