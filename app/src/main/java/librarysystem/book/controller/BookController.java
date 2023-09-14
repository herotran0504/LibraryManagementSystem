package librarysystem.book.controller;

import business.Book;
import business.exception.BookException;
import dataaccess.BookDao;
import librarysystem.utils.Result;

import java.util.List;

public interface BookController {

    Result<Void> addNewBook(Book book) throws BookException;

    Result<List<Book>> getAllBooks() throws BookException;

    static BookController get(BookDao dao) {
        return new BookControllerImpl(dao);
    }
}

