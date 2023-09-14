package dataaccess;

import librarysystem.models.Book;
import librarysystem.models.LibraryMember;
import librarysystem.models.User;

import java.util.HashMap;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);
}
