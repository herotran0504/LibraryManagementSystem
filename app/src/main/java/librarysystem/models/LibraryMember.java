package librarysystem.models;

public class LibraryMember extends Person {

    private String memberId;
    private CheckoutRecord checkoutRecord;
    private CheckoutRecordEntry checkoutRecordEntry;
    private BookCopy numOfCopy;

    public LibraryMember() {
    }

    public LibraryMember(String memberId, String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }

    public CheckoutRecordEntry getCheckoutRecordEntry() {
        return checkoutRecordEntry;
    }

    public void setCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
        this.checkoutRecordEntry = checkoutRecordEntry;
    }

    public BookCopy getNumOfCopy() {
        return numOfCopy;
    }

    public void setNumOfCopy(BookCopy numOfCopy) {
        this.numOfCopy = numOfCopy;
    }

    @Override
    public String toString() {
        return "Member id:" + memberId + " First Name:" + getFirstname() + " Last Name:" + getLastName();
    }
}