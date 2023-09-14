package librarysystem.book.controller;

import business.Book;
import dataaccess.BookDao;
import librarysystem.utils.Result;

public interface PublicationController {

    Result addNewBook(Book book) throws Exception;

    static PublicationController get(BookDao dao) {
        return new PublicationControllerImpl(dao);
    }

    Result getAllPublications() throws Result;
}

