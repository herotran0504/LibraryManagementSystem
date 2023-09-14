package librarysystem.book.controller;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.exception.CheckoutException;
import dataaccess.CheckoutDao;
import librarysystem.utils.Result;

import java.util.List;

public class CheckoutControllerImpl implements CheckoutController {
    private final CheckoutDao checkoutDao;

    CheckoutControllerImpl(CheckoutDao checkoutDao) {
        this.checkoutDao = checkoutDao;
    }

    @Override
    public Result<Void> save(CheckoutRecord record) throws CheckoutException {
        try {
            checkoutDao.save(record);
            return new Result<>(true, "Successfully added");
        } catch (Exception e) {
            throw new CheckoutException(e);
        }
    }

    @Override
    public CheckoutRecord getCheckoutRecord(LibraryMember member) {
        return new CheckoutRecord(member);
    }

    @Override
    public Result<List<CheckoutRecordEntry>> getCheckoutDetail(String memberId) throws CheckoutException {
        try {
            List<CheckoutRecordEntry> entry = checkoutDao.findCheckOutRecord(memberId);
            return new Result<>(true, "Successfully fetched", entry);
        } catch (Exception e) {
            throw new CheckoutException(e);
        }
    }

    @Override
    public List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws CheckoutException {
        return checkoutDao.getCheckoutRecordEntries();
    }
}
