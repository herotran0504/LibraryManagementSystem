package librarysystem.book.controller;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.CheckoutDao;
import librarysystem.utils.Result;

import java.util.List;

public class CheckoutControllerImpl implements CheckoutController {
    private final CheckoutDao checkoutDao;

    CheckoutControllerImpl(CheckoutDao checkoutDao) {
        this.checkoutDao = checkoutDao;
    }

    @Override
    public Result save(CheckoutRecord record) {
        try {
            checkoutDao.save(record);
            return new Result(true, "Successfully added");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    @Override
    public CheckoutRecord getCheckoutRecord(LibraryMember member) {
        return new CheckoutRecord(member);
    }

    @Override
    public Result getCheckoutDetail(String memberId) {
        try {
            List<CheckoutRecordEntry> entry = checkoutDao.findCheckOutRecord(memberId);
            return new Result(true, "Successfully fetched", entry);
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    @Override
    public List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws Result {
        return checkoutDao.getCheckoutRecordEntries();
    }
}