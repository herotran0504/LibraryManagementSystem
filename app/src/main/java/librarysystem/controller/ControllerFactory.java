package librarysystem.controller;

import dataaccess.DaoFactory;

public interface ControllerFactory {
    CheckoutController getCheckoutController();

    LibraryMemberController getLibraryMemberController();

    UserController getUserController();

    PublicationController getPublicationController();

    static ControllerFactory get() {
        return new ControllerFactoryImpl(DaoFactory.getDaoFactory());
    }
}


