package librarysystem.controller;

import librarysystem.dao.CheckoutDao;
import librarysystem.models.CheckoutRecord;
import librarysystem.models.CheckoutRecordEntry;
import librarysystem.models.LibraryMember;
import librarysystem.util.Result;

import java.util.List;

public class CheckoutController {
    private final CheckoutDao checkoutDao;

    CheckoutController(CheckoutDao checkoutDao) {
        this.checkoutDao = checkoutDao;
    }

    public Result save(CheckoutRecord record) {
        try {
            checkoutDao.save(record);
            return new Result(true, "Successfully added");
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public CheckoutRecord getCheckoutRecord(LibraryMember member) {
        return new CheckoutRecord(member);
    }

    public Result getCheckoutDetail(String memberId) {
        try {
            List<CheckoutRecordEntry> entry = checkoutDao.findCheckOutRecord(memberId);
            return new Result(true, "Successfully fetched", entry);
        } catch (Exception e) {
            return new Result(false, Result.getRuntimeException());
        }
    }

    public List<CheckoutRecordEntry> getAllCheckoutRecordEntries() throws Result {
        return checkoutDao.getCheckoutRecordEntries();
    }
}
