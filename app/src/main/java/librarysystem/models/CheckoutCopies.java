package librarysystem.models;

import librarysystem.util.Result;
import librarysystem.utils.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutCopies {
    private final Map<String, CheckoutRecordEntry> checkedOutCopies = new HashMap<>();

    public CheckoutCopies(List<CheckoutRecordEntry> checkoutEntries) {
        for (CheckoutRecordEntry e : checkoutEntries) {
            String key = e.getCopy().getPrimaryKey();
            checkedOutCopies.put(key, e);
        }
    }

    public String getCheckoutDate(BookCopy copy) {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            return checkedOutCopies.get(copy.getPrimaryKey()).getCheckoutDate();
        } else {
            return "";
        }
    }

    public String getDueDate(BookCopy copy) {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            return checkedOutCopies.get(copy.getPrimaryKey()).getDueDate();
        } else {
            return "";
        }
    }

    public String getStatus(BookCopy copy) throws Result {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            try {
                Date dueDate = DateUtil.parse(checkedOutCopies.get(copy.getPrimaryKey()).getDueDate());
                Date currentDate = new Date();
                if (dueDate.compareTo(currentDate) > 0) {
                    return "CHECKED OUT";
                } else {
                    return "OVERDUE";
                }
            } catch (ParseException e) {
                throw new Result(false, e.getMessage());
            }
        } else {
            return "AVAILABLE";
        }
    }

    public String getFirstNameOfMember(BookCopy copy) {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            return checkedOutCopies.get(copy.getPrimaryKey()).getCheckoutRecord().getLibraryMember().getFirstname();
        } else {
            return "";
        }
    }

    public String getLastNameOfMember(BookCopy copy) {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            return checkedOutCopies.get(copy.getPrimaryKey()).getCheckoutRecord().getLibraryMember().getLastName();
        } else {
            return "";
        }
    }

    public String getCheckingMemberId(BookCopy copy) {
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            return checkedOutCopies.get(copy.getPrimaryKey()).getCheckoutRecord().getLibraryMember().getMemberId();
        } else {
            return "";
        }
    }

}
