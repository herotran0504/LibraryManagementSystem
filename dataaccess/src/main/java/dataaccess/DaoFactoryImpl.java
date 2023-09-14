package dataaccess;

class DaoFactoryImpl implements DaoFactory {

    private final LibraryMemberDao libraryMemberDao;
    private final BookDao bookDao;
    private final UserDao userDao;
    private final CheckoutDao checkoutDao;

    DaoFactoryImpl() {
        libraryMemberDao = new LibraryMemberDaoImpl();
        bookDao = new BookDaoImpl();
        userDao = new UserDaoImpl();
        checkoutDao = new CheckoutDaoImpl(bookDao);
    }

    @Override
    public LibraryMemberDao getLibraryMemberDao() {
        return libraryMemberDao;
    }

    @Override
    public BookDao getBookDao() {
        return bookDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public CheckoutDao getCheckoutDao() {
        return checkoutDao;
    }

}