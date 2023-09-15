package librarysystem.controller;

import dataaccess.DaoFactory;
import librarysystem.book.controller.CheckoutController;
import librarysystem.member.controller.LibraryMemberController;

class ControllerFactoryImpl implements ControllerFactory {

    private final DaoFactory daoFactory;

    ControllerFactoryImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public CheckoutController getCheckoutController() {
        return CheckoutController.get(daoFactory.getCheckoutDao());
    }

    @Override
    public LibraryMemberController getLibraryMemberController() {
        return LibraryMemberController.get(daoFactory.getLibraryMemberDao());
    }

}
