package librarysystem.util;

public class Result extends Exception {

    private static final String RUNTIME_EXCEPTION = "Something went wrong. Please contact to administrator";

    private Boolean success;
    private String message;
    private Object data;

    public Result(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public Result(Boolean success, String message, Object data) {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getRuntimeException() {
        return RUNTIME_EXCEPTION;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
