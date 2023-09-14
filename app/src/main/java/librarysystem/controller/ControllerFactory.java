package librarysystem.controller;

import dataaccess.DaoFactory;
import librarysystem.book.controller.CheckoutController;
import librarysystem.book.controller.BookController;
import librarysystem.member.controller.LibraryMemberController;
import librarysystem.user.controller.UserController;

public interface ControllerFactory {
    CheckoutController getCheckoutController();

    LibraryMemberController getLibraryMemberController();

    UserController getUserController();

    BookController getBookController();

    static ControllerFactory get() {
        return new ControllerFactoryImpl(DaoFactory.getDaoFactory());
    }
}


