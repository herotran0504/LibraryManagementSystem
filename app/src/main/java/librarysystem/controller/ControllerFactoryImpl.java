package librarysystem.controller;

import dataaccess.DaoFactory;
import librarysystem.book.controller.CheckoutController;
import librarysystem.book.controller.BookController;
import librarysystem.member.controller.LibraryMemberController;
import librarysystem.user.controller.UserController;

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

    @Override
    public UserController getUserController() {
        return UserController.get(daoFactory.getUserDao());
    }

    @Override
    public BookController getBookController() {
        return BookController.get(daoFactory.getBookDao());
    }

}
