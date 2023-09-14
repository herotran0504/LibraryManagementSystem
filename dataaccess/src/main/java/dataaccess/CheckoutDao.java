package dataaccess;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import librarysystem.utils.Result;

import java.util.List;

public interface CheckoutDao {
    void save(CheckoutRecord checkoutRecord) throws Result;

    List<CheckoutRecordEntry> findCheckOutRecord(String memberId) throws Result;

    List<CheckoutRecordEntry> getCheckoutRecordEntries() throws Result;
}
