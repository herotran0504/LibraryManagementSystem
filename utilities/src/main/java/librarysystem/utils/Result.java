package librarysystem.utils;

public class Result<T> {

    private static final String RUNTIME_EXCEPTION = "Something went wrong. Please contact to administrator";

    private final Boolean success;
    private final String message;
    private T data;

    public Result(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public Result(Boolean success, String message, T data) {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public static String getRuntimeException() {
        return RUNTIME_EXCEPTION;
    }

    public T getData() {
        return data;
    }

}
