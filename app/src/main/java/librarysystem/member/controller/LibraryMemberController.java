package librarysystem.member.controller;

import business.LibraryMember;
import dataaccess.LibraryMemberDao;
import librarysystem.utils.Result;

public interface LibraryMemberController {
    Result addNewMember(LibraryMember libraryMember);

    Result getMembers();

    Result getMember(String id);

    Result updateMember(LibraryMember libraryMember);

    Result deleteMember(String id);

    static LibraryMemberController get(LibraryMemberDao libraryMemberDao) {
        return new LibraryMemberControllerImpl(libraryMemberDao);
    }
}

