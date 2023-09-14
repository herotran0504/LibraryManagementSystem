package librarysystem.book.controller;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.CheckoutDao;
import librarysystem.utils.Result;

import java.util.List;

public interface CheckoutController {

    Result save(CheckoutRecord record);

    CheckoutRecord getCheckoutRecord(LibraryMember member);

    Result getCheckoutDetail(String memberId);

    List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws Result;

    static CheckoutController get(CheckoutDao checkoutDao) {
        return new CheckoutControllerImpl(checkoutDao);
    }
}

