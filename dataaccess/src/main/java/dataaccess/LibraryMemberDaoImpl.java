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
        readMemberMap();
        System.out.println("Add: " + libraryMember);
        members.put(libraryMember.getMemberId(), libraryMember);
        FileOperation.saveToStorage(StorageType.MEMBERS, members);
    }

    @Override
    public void updateLibraryMember(LibraryMember libraryMember) throws Result {
        addLibraryMember(libraryMember);
    }

    @Override
    public void deleteLibraryMember(String id) throws Result {
        readMemberMap();
        LibraryMember mem = members.remove(id);
        System.out.println("Delete: " + mem);
        FileOperation.saveToStorage(StorageType.MEMBERS, members);
    }

    @Override
    public LibraryMember findLibraryMember(String memberId) throws Result {
        readMemberMap();
        if (members.containsKey(memberId)) {
            LibraryMember mem = members.get(memberId);
            System.out.println(mem);
            return mem;
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
        readMemberMap();
        List<LibraryMember> list = new ArrayList<>();
        for (Map.Entry<String, LibraryMember> entry : members.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}
