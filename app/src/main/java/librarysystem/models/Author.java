package librarysystem.models;

import java.util.List;

public class Author extends Person {

    private String credentials;
    private String shortBio;
    private List<Book> bookList;

    public Author() {
    }

    public Author(
            String firstname,
            String lastName,
            String phone,
            Address address,
            String credentials,
            String shortBio,
            List<Book> bookList
    ) {
        super(firstname, lastName, phone, address);
        this.credentials = credentials;
        this.bookList = bookList;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

}