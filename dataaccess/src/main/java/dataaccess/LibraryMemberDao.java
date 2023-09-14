package dataaccess;

import business.LibraryMember;
import business.exception.MemberException;

import java.util.List;

public interface LibraryMemberDao {

    void addLibraryMember(LibraryMember libraryMember) throws MemberException;

    void updateLibraryMember(LibraryMember libraryMember) throws MemberException;

    void deleteLibraryMember(String id) throws MemberException;

    LibraryMember findLibraryMember(String id) throws MemberException;

    List<LibraryMember> findMembers() throws MemberException;
}
