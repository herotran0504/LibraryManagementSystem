package librarysystem.dao;

import librarysystem.models.CheckoutRecord;
import librarysystem.models.CheckoutRecordEntry;
import librarysystem.util.Result;

import java.util.List;

public interface CheckoutDao {
    void save(CheckoutRecord checkoutRecord) throws Result;

    List<CheckoutRecordEntry> findCheckOutRecord(String memberId) throws Result;

    List<CheckoutRecordEntry> getCheckoutRecordEntries() throws Result;
}
