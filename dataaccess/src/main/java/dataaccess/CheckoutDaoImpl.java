package dataaccess;

import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.exception.CheckoutException;
import librarysystem.utils.FileOperation;
import librarysystem.utils.FileOperation.StorageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class CheckoutDaoImpl implements CheckoutDao {

    private static Map<String, List<CheckoutRecordEntry>> checkout;

    private final BookDao bookDao;

    CheckoutDaoImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(CheckoutRecord checkoutRecord) throws CheckoutException {
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
        FileOperation.saveToStorage(StorageType.CHECKOUT, tempCheckout);
        checkout.put(memberId, originalList);
        updateCheckoutBook(getBook(checkoutEntries));
    }

    private static Book getBook(List<CheckoutRecordEntry> checkoutEntries) {
        return checkoutEntries.get(0).getCopy().getBook();
    }

    private void updateCheckoutBook(Book book) throws CheckoutException {
        if (book != null) {
            updateCheckoutCopy(book.getIsbn());
        }
    }

    @Override
    public void updateCheckoutCopy(String isbn) throws CheckoutException {
        try {
            Map<String, Book> books = bookDao.readBookMap();
            Book book;
            if (books.containsKey(isbn)) {
                book = books.get(isbn);
                for (int i = 0; i < book.getCopies().length; i++) {
                    final BookCopy copy = book.getCopies()[i];
                    if (copy.isAvailable()) {
                        copy.changeAvailability();
                        break;
                    }
                }
            }
            FileOperation.saveToStorage(StorageType.BOOKS, books);
        } catch (Exception e) {
            throw new CheckoutException(e);
        }
    }

    public static Map<String, List<CheckoutRecordEntry>> readCheckoutMap() throws CheckoutException {
        if (checkout == null) {
            try {
                checkout = FileOperation.readFromStorageAsMap(StorageType.CHECKOUT);
            } catch (Exception e) {
                throw new CheckoutException(e.getMessage());
            }
        }
        return checkout;
    }

    @Override
    public List<CheckoutRecordEntry> findCheckOutRecord(String memberId) throws CheckoutException {
        List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<>();
        Map<String, List<CheckoutRecordEntry>> entry = readCheckoutMap();
        if (entry.containsKey(memberId)) {
            checkoutRecordEntries = entry.get(memberId);
        }
        return checkoutRecordEntries;
    }

    @Override
    public List<CheckoutRecordEntry> getCheckoutRecordEntries() throws CheckoutException {
        try {
            Map<String, List<CheckoutRecordEntry>> entry = readCheckoutMap();
            List<CheckoutRecordEntry> entries = new ArrayList<>();
            for (Entry<String, List<CheckoutRecordEntry>> e : entry.entrySet()) {
                entries.addAll(e.getValue());
            }
            return entries;
        } catch (Exception e) {
            throw new CheckoutException(e);
        }
    }

}
