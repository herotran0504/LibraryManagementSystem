package dataaccess;

import business.LibraryMember;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryMemberDaoImpl implements LibraryMemberDao {
    private static Map<String, LibraryMember> members;

    @Override
    public void addLibraryMember(LibraryMember libraryMember) throws Result {
        Map<String, LibraryMember> mems = readMemberMap();
        System.out.println("Add: " + libraryMember);
        mems.put(libraryMember.getMemberId(), libraryMember);
        members = mems;
        FileOperation.saveToStorage(StorageType.MEMBERS, mems);
        members.put(libraryMember.getMemberId(), libraryMember);
    }

    @Override
    public void updateLibraryMember(LibraryMember libraryMember) throws Result {
        addLibraryMember(libraryMember);
    }

    @Override
    public void deleteLibraryMember(String id) throws Result {
        Map<String, LibraryMember> mems = readMemberMap();
        LibraryMember mem = mems.remove(id);
        System.out.println("Delete: " + mem);
        FileOperation.saveToStorage(StorageType.MEMBERS, mems);
        members.remove(id);
    }

    @Override
    public LibraryMember findLibraryMember(String memberId) throws Result {
        Map<String, LibraryMember> mems = readMemberMap();
        if (mems.containsKey(memberId)) {
            System.out.println(mems.get(memberId));
            return mems.get(memberId);
        }
        return null;
    }

    public Map<String, LibraryMember> readMemberMap() throws Result {
        if (members == null) {
            try {
                members = FileOperation.readFromStorageAsMap(StorageType.MEMBERS);
            } catch (Exception e) {
                throw new Result(false, e.getMessage());
            }
        }
        return members;
    }

    @Override
    public List<LibraryMember> findMembers() throws Result {
        List<LibraryMember> list = new ArrayList<>();
        Map<String, LibraryMember> hash = readMemberMap();
        for (Map.Entry<String, LibraryMember> entry : hash.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}
