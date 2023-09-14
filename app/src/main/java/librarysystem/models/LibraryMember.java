package librarysystem.models;

public class LibraryMember extends Person {

    private String memberId;
    private CheckoutRecord checkoutRecord;
    private CheckoutRecordEntry checkoutRecordEntry;
    private Copy numOfCopy;

    public LibraryMember() {
    }

    public LibraryMember(
            String memberId,
            String firstName,
            String lastName,
            String phone,
            Address address,
            CheckoutRecord checkoutRecord,
            CheckoutRecordEntry checkoutRecordEntry,
            Copy numOfCopy
    ) {
        super(firstName, lastName, phone, address);
        this.memberId = memberId;
        this.checkoutRecord = checkoutRecord;
        this.checkoutRecordEntry = checkoutRecordEntry;
        this.numOfCopy = numOfCopy;
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

    public Copy getNumOfCopy() {
        return numOfCopy;
    }

    public void setNumOfCopy(Copy numOfCopy) {
        this.numOfCopy = numOfCopy;
    }

    @Override
    public String toString() {
        return "Member id:" + memberId + " First Name:" + getFirstname() + " Last Name:" + getLastName();
    }
}