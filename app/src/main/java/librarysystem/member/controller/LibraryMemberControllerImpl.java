package librarysystem.member.controller;

import business.LibraryMember;
import dataaccess.LibraryMemberDao;
import librarysystem.util.Const;
import librarysystem.util.MemberIdHelper;
import librarysystem.utils.Result;

import java.util.List;

class LibraryMemberControllerImpl implements LibraryMemberController {
    private final LibraryMemberDao libraryMemberDao;

    LibraryMemberControllerImpl(LibraryMemberDao libraryMemberDao) {
        this.libraryMemberDao = libraryMemberDao;
    }

    @Override
    public Result<Void> addNewMember(LibraryMember libraryMember) {
        try {
            libraryMember.setMemberId(MemberIdHelper.getNextID(Const.MEMBER_PROPERTY_KEY));
            libraryMemberDao.addLibraryMember(libraryMember);
            return new Result<>(true, "Successfully added");
        } catch (Exception e) {
            return new Result<>(false, Result.getRuntimeException());
        }
    }

    @Override
    public Result<List<LibraryMember>> getMembers() {
        try {
            List<LibraryMember> list = libraryMemberDao.findMembers();
            return new Result<>(true, "Successfully added", list);
        } catch (Exception e) {
            return new Result<>(false, Result.getRuntimeException());
        }
    }

    @Override
    public Result<LibraryMember> getMember(String id) {
        try {
            LibraryMember libraryMember = libraryMemberDao.findLibraryMember(id);
            if (libraryMember != null) {
                return new Result<>(true, "Successfully fetched", libraryMember);
            } else {
                return new Result<>(false, "Member doesn't exist");
            }
        } catch (Exception e) {
            return new Result<>(false, Result.getRuntimeException());
        }
    }

    @Override
    public Result<Void> updateMember(LibraryMember libraryMember) {
        try {
            libraryMemberDao.updateLibraryMember(libraryMember);
            return new Result<>(true, "Successfully updated");
        } catch (Exception e) {
            return new Result<>(false, Result.getRuntimeException());
        }
    }

    @Override
    public Result<Void> deleteMember(String id) {
        try {
            libraryMemberDao.deleteLibraryMember(id);
            return new Result<>(true, "Successfully deleted");
        } catch (Exception e) {
            return new Result<>(false, Result.getRuntimeException());
        }
    }
}
