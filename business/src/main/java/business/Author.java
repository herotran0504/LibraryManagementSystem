package business;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

    private String bio;

    private final List<Book> bookList = new ArrayList<>();
    public Author() {
    }

    public Author(String firstname, String lastName, String phone, Address address, String bio) {
        super(firstname, lastName, phone, address);
        this.bio = bio;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

}