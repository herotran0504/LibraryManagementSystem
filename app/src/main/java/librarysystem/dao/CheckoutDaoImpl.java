package librarysystem.dao;

import librarysystem.models.*;
import librarysystem.util.FileOperation;
import librarysystem.util.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static librarysystem.util.FileOperation.StorageType.CHECKOUT;

class CheckoutDaoImpl implements CheckoutDao {

    private static Map<String, List<CheckoutRecordEntry>> checkout;

    @Override
    public void save(CheckoutRecord checkoutRecord) throws Result {
        Map<String, List<CheckoutRecordEntry>> tempCheckout = readCheckoutMap();
        List<CheckoutRecordEntry> originalList = null;

        final String memberId = checkoutRecord.getLibraryMember().getMemberId();
        if (!tempCheckout.isEmpty()) {
            originalList = tempCheckout.get(memberId);
        }
        if (originalList == null) {
            originalList = new ArrayList<>();
        }
        final List<CheckoutRecordEntry> checkoutEntries = checkoutRecord.getCheckoutEntries();
        originalList.addAll(checkoutEntries);

        tempCheckout.put(memberId, originalList);
        checkout = tempCheckout;
        FileOperation.saveToStorage(CHECKOUT, tempCheckout);
        checkout.put(memberId, originalList);
        updateCheckoutPublication(checkoutEntries.get(0).getCopy().getBook());
    }

    private void updateCheckoutPublication(Book publication) throws Result {
        if (publication instanceof Book) {
            BookDao bookDao = new BookDaoImpl();
            bookDao.updateCheckoutCopy(publication.getIsbn());
        }
    }

    public Map<String, List<CheckoutRecordEntry>> readCheckoutMap() throws Result {
        if (checkout == null) {
            try {
                checkout = FileOperation.readFromStorageAsMap(CHECKOUT);
            } catch (Exception e) {
                throw new Result(false, e.getMessage());
            }
        }
        return checkout;
    }

    @Override
    public List<CheckoutRecordEntry> findCheckOutRecord(String memberId) throws Result {
        List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<>();
        Map<String, List<CheckoutRecordEntry>> entry = readCheckoutMap();
        if (entry.containsKey(memberId)) {
            checkoutRecordEntries = entry.get(memberId);
        }
        return checkoutRecordEntries;
    }

    @Override
    public List<CheckoutRecordEntry> getCheckoutRecordEntries() throws Result {
        Map<String, List<CheckoutRecordEntry>> entry = readCheckoutMap();
        List<CheckoutRecordEntry> entries = new ArrayList<>();
        for (Entry<String, List<CheckoutRecordEntry>> e : entry.entrySet()) {
            entries.addAll(e.getValue());
        }
        return entries;
    }

}
