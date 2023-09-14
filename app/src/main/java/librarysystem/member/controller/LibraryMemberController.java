package librarysystem.member.controller;

import business.LibraryMember;
import dataaccess.LibraryMemberDao;
import librarysystem.utils.Result;

import java.util.List;

public interface LibraryMemberController {
    Result<Void> addNewMember(LibraryMember libraryMember);

    Result<List<LibraryMember>> getMembers();

    Result<LibraryMember> getMember(String id);

    Result<Void> updateMember(LibraryMember libraryMember);

    Result<Void> deleteMember(String id);

    static LibraryMemberController get(LibraryMemberDao dao) {
        return new LibraryMemberControllerImpl(dao);
    }
}

