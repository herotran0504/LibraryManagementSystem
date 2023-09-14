package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {

    private final List<CheckoutRecordEntry> checkoutEntries = new ArrayList<>();

    private final LibraryMember libraryMember;

    public CheckoutRecord(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public List<CheckoutRecordEntry> getCheckoutEntries() {
        return checkoutEntries;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

}