package librarysystem.models;

import java.io.Serializable;

public class CheckoutRecordEntry implements Serializable {

    private final String checkoutDate;
    private final String dueDate;
    private final Copy copy;
    private final CheckoutRecord checkoutRecord;

    public CheckoutRecordEntry(
            String checkoutDate,
            String dueDate,
            Copy copy,
            CheckoutRecord checkoutRecord
    ) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.copy = copy;
        this.checkoutRecord = checkoutRecord;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Copy getCopy() {
        return copy;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }
}