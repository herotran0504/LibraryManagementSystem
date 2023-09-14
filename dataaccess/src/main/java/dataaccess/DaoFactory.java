package dataaccess;

public interface  DaoFactory {

    LibraryMemberDao getLibraryMemberDao();

    BookDao getBookDao();

    UserDao getUserDao();

    CheckoutDao getCheckoutDao();

    static DaoFactory getDaoFactory() {
        return new DaoFactoryImpl();
    }
}
