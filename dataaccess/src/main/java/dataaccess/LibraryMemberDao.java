package dataaccess;

import business.LibraryMember;
import librarysystem.utils.Result;

import java.util.List;

public interface LibraryMemberDao {

    void addLibraryMember(LibraryMember libraryMember) throws Result;

    void updateLibraryMember(LibraryMember libraryMember) throws Result;

    void deleteLibraryMember(String id) throws Result;

    LibraryMember findLibraryMember(String id) throws Result;

    List<LibraryMember> findMembers() throws Result;
}
