package librarysystem.dao;

import librarysystem.models.LibraryMember;
import librarysystem.util.Result;

import java.util.List;

public interface LibraryMemberDao {

    void addLibraryMember(LibraryMember libraryMember) throws Result;

    void updateLibraryMember(LibraryMember libraryMember) throws Result;

    void deleteLibraryMember(String id) throws Result;

    LibraryMember findLibraryMember(String id) throws Result;

    List<LibraryMember> findMembers() throws Result;
}
