package librarysystem.dao;

import librarysystem.models.Book;
import librarysystem.models.BookCopy;
import librarysystem.util.FileOperation;
import librarysystem.util.FileOperation.StorageType;
import librarysystem.util.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class BookDaoImpl implements BookDao {
    private static Map<String, Book> books;

    @Override
    public void addBook(Book newBook) throws Result {
        Map<String, Book> book = readBookMap();
        book.put(newBook.getIsbn(), newBook);
        books = book;
        FileOperation.saveToStorage(StorageType.BOOKS, book);
        books.put(newBook.getIsbn(), newBook);
    }

    @Override
    public void updateBook(Book newBook) throws Result {
        addBook(newBook);
    }

    @Override
    public void deleteBook(String ISBN) throws Result {
        Map<String, Book> book = readBookMap();
        book.remove(ISBN);
        FileOperation.saveToStorage(StorageType.BOOKS, book);
        books.remove(ISBN);
    }

    @Override
    public Book findBook(String ISBN) throws Result {
        return null;
    }

    public Map<String, Book> readBookMap() throws Result {
        if (books == null) {
            try {
                books = FileOperation.readFromStorageAsMap(StorageType.BOOKS);
            } catch (Exception e) {
                throw new Result(false, e.getMessage());
            }
        }
        return books;
    }

    @Override
    public List<Book> getAll() throws Result {
        List<Book> result = new ArrayList<>();
        for (Entry<String, Book> e : readBookMap().entrySet()) {
            result.add(e.getValue());
        }
        return result;
    }

    @Override
    public void updateCheckoutCopy(String publicationId) throws Result {
        Map<String, Book> books = readBookMap();

        Book book;
        if (books.containsKey(publicationId)) {
            book = books.get(publicationId);
            for (int i = 0; i < book.getCopies().length; i++) {
                final BookCopy copy = book.getCopies()[i];
                if (!copy.isAvailable()) {
                    copy.changeAvailability();
                    break;
                }
            }
        }

        FileOperation.saveToStorage(StorageType.BOOKS, books);
    }

}
