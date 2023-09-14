package dataaccess;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.exception.CheckoutException;

import java.util.List;

public interface CheckoutDao {
    void save(CheckoutRecord checkoutRecord) throws CheckoutException;

    List<CheckoutRecordEntry> findCheckOutRecord(String memberId) throws CheckoutException;

    List<CheckoutRecordEntry> getCheckoutRecordEntries() throws CheckoutException;

    void updateCheckoutCopy(String publicationId) throws CheckoutException;
}
