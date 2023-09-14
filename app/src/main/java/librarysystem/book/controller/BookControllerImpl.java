package librarysystem.book.controller;

import business.Book;
import business.exception.BookException;
import dataaccess.BookDao;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;

class BookControllerImpl implements BookController {

    private final BookDao dao;

    BookControllerImpl(BookDao dao) {
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
}
