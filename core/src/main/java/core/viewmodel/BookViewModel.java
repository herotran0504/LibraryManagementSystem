package core.viewmodel;

import business.Book;
import business.exception.BookException;
import librarysystem.utils.Result;

import java.util.List;

public interface BookViewModel extends ViewModel {
    Result<Void> addNewBook(Book book) throws BookException;

    Result<List<Book>> getAllBooks() throws BookException;
}
