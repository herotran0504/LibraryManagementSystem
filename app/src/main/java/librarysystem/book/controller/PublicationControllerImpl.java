package librarysystem.book.controller;

import business.Book;
import dataaccess.BookDao;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;

class PublicationControllerImpl implements PublicationController {

    private final BookDao dao;

    PublicationControllerImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Result addNewBook(Book book) throws Exception {
        dao.addBook(book);
        return new Result(true, "The book has been added.");
    }

    @Override
    public Result getAllPublications() throws Result {
        List<Book> allPublications = new ArrayList<>(dao.getAll());
        Result response = new Result(true, "All Publications Retrived");
        response.setData(allPublications);
        return response;
    }
}
