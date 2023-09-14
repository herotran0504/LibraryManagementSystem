package dataaccess;

import business.Book;
import business.exception.BookException;

import java.util.List;
import java.util.Map;

public interface BookDao {

    void addBook(Book book) throws BookException;

    void updateBook(Book newBook) throws BookException;

    void deleteBook(String id) throws BookException;

    Map<String, Book> readBookMap() throws BookException;

    List<Book> getAll() throws BookException;
}
