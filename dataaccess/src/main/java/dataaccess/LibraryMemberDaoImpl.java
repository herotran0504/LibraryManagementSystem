package dataaccess;

import business.LibraryMember;
import business.exception.MemberException;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;
import librarysystem.utils.SimpleLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryMemberDaoImpl implements LibraryMemberDao {
    private static Map<String, LibraryMember> members;

    @Override
    public void addLibraryMember(LibraryMember libraryMember) throws MemberException {
        readMemberMap();
        SimpleLogger.logDebug("Add: " + libraryMember);
        members.put(libraryMember.getMemberId(), libraryMember);
        FileOperation.saveToStorage(StorageType.MEMBERS, members);
    }

    @Override
    public void updateLibraryMember(LibraryMember libraryMember) throws MemberException {
        addLibraryMember(libraryMember);
    }

    @Override
    public void deleteLibraryMember(String id) throws MemberException {
        readMemberMap();
        LibraryMember mem = members.remove(id);
        SimpleLogger.logDebug("Delete: " + mem);
        FileOperation.saveToStorage(StorageType.MEMBERS, members);
    }

    @Override
    public LibraryMember findLibraryMember(String memberId) throws MemberException {
        readMemberMap();
        if (members.containsKey(memberId)) {
            LibraryMember mem = members.get(memberId);
            SimpleLogger.logDebug(mem);
            return mem;
        }
        return null;
    }

    public Map<String, LibraryMember> readMemberMap() throws MemberException {
        if (members == null) {
            try {
                members = FileOperation.readFromStorageAsMap(StorageType.MEMBERS);
            } catch (Exception e) {
                throw new MemberException(e.getMessage());
            }
        }
        return members;
    }

    @Override
    public List<LibraryMember> findMembers() throws MemberException {
        readMemberMap();
        List<LibraryMember> list = new ArrayList<>();
        for (Map.Entry<String, LibraryMember> entry : members.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}
