package dataaccess;

import business.Book;
import business.exception.BookException;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class BookDaoImpl implements BookDao {
    private static Map<String, Book> books;

    @Override
    public void addBook(Book newBook) throws BookException {
        Map<String, Book> book = readBookMap();
        book.put(newBook.getIsbn(), newBook);
        books = book;
        FileOperation.saveToStorage(StorageType.BOOKS, book);
        books.put(newBook.getIsbn(), newBook);
    }

    @Override
    public void updateBook(Book newBook) throws BookException {
        addBook(newBook);
    }

    @Override
    public void deleteBook(String ISBN) throws BookException {
        Map<String, Book> book = readBookMap();
        book.remove(ISBN);
        FileOperation.saveToStorage(StorageType.BOOKS, book);
        books.remove(ISBN);
    }

    public Map<String, Book> readBookMap() throws BookException {
        if (books == null) {
            try {
                books = FileOperation.readFromStorageAsMap(StorageType.BOOKS);
            } catch (Exception e) {
                throw new BookException(e.getMessage());
            }
        }
        return books;
    }

    @Override
    public List<Book> getAll() throws BookException {
        List<Book> result = new ArrayList<>();
        for (Entry<String, Book> e : readBookMap().entrySet()) {
            result.add(e.getValue());
        }
        return result;
    }

}
