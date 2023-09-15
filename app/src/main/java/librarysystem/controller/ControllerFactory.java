package librarysystem.controller;

import dataaccess.DaoFactory;
import librarysystem.book.controller.CheckoutController;
import librarysystem.member.controller.LibraryMemberController;

public interface ControllerFactory {
    CheckoutController getCheckoutController();

    LibraryMemberController getLibraryMemberController();

    static ControllerFactory get() {
        return new ControllerFactoryImpl(DaoFactory.getDaoFactory());
    }
}


