package librarysystem.controller;

import librarysystem.dao.LibraryMemberDao;
import librarysystem.models.LibraryMember;
import librarysystem.util.Const;
import librarysystem.util.IdManager;
import librarysystem.util.Result;

import java.util.List;

public class LibraryMemberController {
    private final LibraryMemberDao libraryMemberDao;

    LibraryMemberController(LibraryMemberDao libraryMemberDao) {
        this.libraryMemberDao = libraryMemberDao;
    }

    public Result addNewMember(LibraryMember libraryMember) {
        try {
            libraryMember.setMemberId(IdManager.getNextID(Const.MEMBER_PROPERTY_KEY));
            libraryMemberDao.addLibraryMember(libraryMember);
            System.out.println(libraryMember);
            return new Result(true, "Successfully added");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public Result getMembers() {
        try {
            List<LibraryMember> list = libraryMemberDao.findMembers();
            return new Result(true, "Successfully added", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, Result.getRuntimeException());
        }
    }

    public Result getMember(String id) {
        try {
            LibraryMember libraryMemeber = libraryMemberDao.findLibraryMember(id);
            if (libraryMemeber != null) {
                return new Result(true, "Successfully fetched", libraryMemeber);
            } else {
                return new Result(false, "Member doesn't exist");
            }
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public Result updateMember(LibraryMember libraryMember) {
        try {
            libraryMemberDao.updateLibraryMember(libraryMember);
            System.out.println(libraryMember);
            return new Result(true, "Successfully updated");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public Result deleteMember(String id) {
        try {
            libraryMemberDao.deleteLibraryMember(id);
            return new Result(true, "Successfully deleted");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }
}
