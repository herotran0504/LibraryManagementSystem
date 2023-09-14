package mock;

import business.Book;
import business.LibraryMember;
import business.User;

import java.util.HashMap;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);
}
