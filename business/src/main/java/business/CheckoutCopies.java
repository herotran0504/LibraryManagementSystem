package business;

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

    public Map<String, CheckoutRecordEntry> getCheckedOutCopies() {
        return checkedOutCopies;
    }

}
