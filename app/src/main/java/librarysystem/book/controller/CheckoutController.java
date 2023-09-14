package librarysystem.book.controller;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.exception.CheckoutException;
import dataaccess.CheckoutDao;
import librarysystem.utils.Result;

import java.util.List;

public interface CheckoutController {

    Result<Void> save(CheckoutRecord record) throws CheckoutException;

    CheckoutRecord getCheckoutRecord(LibraryMember member) throws CheckoutException;

    Result<List<CheckoutRecordEntry>> getCheckoutDetail(String memberId) throws CheckoutException;

    List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws CheckoutException;

    static CheckoutController get(CheckoutDao checkoutDao) {
        return new CheckoutControllerImpl(checkoutDao);
    }
}

