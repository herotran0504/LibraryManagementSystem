package business.exception;

public class CheckoutException extends BookException {
    public CheckoutException() {
    }

    public CheckoutException(String message) {
        super(message);
    }

    public CheckoutException(Exception e) {
        super(e.getMessage());
    }
}
