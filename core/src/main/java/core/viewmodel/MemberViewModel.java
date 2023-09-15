package core.viewmodel;

import business.LibraryMember;
import librarysystem.utils.Result;

import java.util.List;

public interface MemberViewModel extends ViewModel {
    Result<Void> addNewMember(LibraryMember libraryMember);

    Result<List<LibraryMember>> getMembers();

    Result<LibraryMember> getMember(String id);

    Result<Void> updateMember(LibraryMember libraryMember);

    Result<Void> deleteMember(String id);
}
