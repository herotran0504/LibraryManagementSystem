package librarysystem.dao;

class DaoFactoryImpl implements DaoFactory {

    @Override
    public LibraryMemberDao getLibraryMemberDao() {
        return new LibraryMemberDaoImpl();
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoImpl();
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public CheckoutDao getCheckoutDao() {
        return new CheckoutDaoImpl();
    }

}