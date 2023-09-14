package business.exception;

public class LoginException extends UserException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Exception e) {
        super(e.getMessage());
    }
}
