package librarysystem.models;

import java.io.Serializable;
import java.util.List;

public class Book extends Publication {

    /**
     *
     */
    private static final long serialVersionUID = 7221997519867082982L;
    private String isbn;
    private List<Author> authorList;

    public Book() {
    }


    public Book(String title, String isbn, String maxCheckoutLength,
                List<Author> authorList) {
        super(title);
        this.isbn = isbn;
        this.authorList = authorList;
    }


    public String getISBN() {
        return isbn;
    }

    public void setISBN(String iSBN) {
        isbn = iSBN;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String getPublicationId() {
        return isbn;
    }


    @Override
    public String getPrimaryKey() {
        return isbn;
    }

}