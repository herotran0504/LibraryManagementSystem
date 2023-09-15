package book.viewmodel;

import business.Book;
import business.exception.BookException;
import core.viewmodel.BookViewModel;
import dataaccess.BookDao;
import dataaccess.DaoFactory;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;

public class BookViewModelImpl implements BookViewModel {
    private final BookDao dao;

    BookViewModelImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Result<Void> addNewBook(Book book) throws BookException {
        dao.addBook(book);
        return new Result<>(true, "The book has been added.");
    }

    @Override
    public Result<List<Book>> getAllBooks() throws BookException {
        List<Book> books = new ArrayList<>(dao.getAll());
        return new Result<>(true, "All books Retrieved", books);
    }

    public static BookViewModel create() {
        return new BookViewModelImpl(DaoFactory.getDaoFactory().getBookDao());
    }
}
