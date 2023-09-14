package librarysystem.controller;

import librarysystem.dao.BookDao;
import librarysystem.models.Book;
import librarysystem.util.Result;

import java.util.ArrayList;
import java.util.List;

public class PublicationController {

    private final BookDao dao;

    PublicationController(BookDao dao) {
        this.dao = dao;
    }

    public Result addNewBook(Book book) throws Exception {
        dao.addBook(book);
        return new Result(true, "The book has been added.");
    }

    public Result getAllPublications() throws Result {
        List<Book> allPublications = new ArrayList<>(dao.getAll());
        Result response = new Result(true, "All Publications Retrived");
        response.setData(allPublications);
        return response;
    }
}
