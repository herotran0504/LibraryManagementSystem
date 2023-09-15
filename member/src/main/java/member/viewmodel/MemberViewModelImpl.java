package member.viewmodel;

import business.LibraryMember;
import core.util.MemberIdHelper;
import core.viewmodel.MemberViewModel;
import dataaccess.DaoFactory;
import dataaccess.LibraryMemberDao;
import librarysystem.utils.Result;

import java.util.List;

public class MemberViewModelImpl implements MemberViewModel {
    private final static String MEMBER_PROPERTY_KEY = "librarymember_id";

    private final LibraryMemberDao libraryMemberDao;

    MemberViewModelImpl(LibraryMemberDao libraryMemberDao) {
        this.libraryMemberDao = libraryMemberDao;
    }

    @Override
    public Result<Void> addNewMember(LibraryMember libraryMember) {
        try {
            libraryMember.setMemberId(MemberIdHelper.getNextID(MEMBER_PROPERTY_KEY));
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

    public static MemberViewModel create() {
        return new MemberViewModelImpl(DaoFactory.getDaoFactory().getLibraryMemberDao());
    }
}
