package core.viewmodel;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.exception.CheckoutException;
import librarysystem.utils.Result;

import java.util.List;

public interface CheckoutViewModel extends ViewModel {
    Result<Void> save(CheckoutRecord record) throws CheckoutException;

    CheckoutRecord getCheckoutRecord(LibraryMember member) throws CheckoutException;

    Result<List<CheckoutRecordEntry>> getCheckoutDetail(String memberId) throws CheckoutException;

    List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws CheckoutException;
}
