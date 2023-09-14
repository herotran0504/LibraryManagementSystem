package dataaccess;

import business.Book;
import librarysystem.utils.Result;

import java.util.List;

public interface BookDao {

    void addBook(Book book) throws Result;

    void updateBook(Book newBook) throws Result;

    void deleteBook(String id) throws Result;

    Book findBook(String id) throws Result;

    List<Book> getAll() throws Result;

    void updateCheckoutCopy(String publicationId) throws Result;
}
