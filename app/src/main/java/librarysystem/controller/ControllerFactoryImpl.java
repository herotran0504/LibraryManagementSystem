package librarysystem.controller;

import dataaccess.DaoFactory;

class ControllerFactoryImpl implements ControllerFactory {

    private final DaoFactory daoFactory;

    ControllerFactoryImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public CheckoutController getCheckoutController() {
        return new CheckoutController(daoFactory.getCheckoutDao());
    }

    @Override
    public LibraryMemberController getLibraryMemberController() {
        return new LibraryMemberController(daoFactory.getLibraryMemberDao());
    }

    @Override
    public UserController getUserController() {
        return new UserController(daoFactory.getUserDao());
    }

    @Override
    public PublicationController getPublicationController() {
        return new PublicationController(daoFactory.getBookDao());
    }

}
