package dev.kamlesh.paymenthelper.exceptions;

public class NullIDException extends Exception{

    public NullIDException() {
        super();
    }

    public NullIDException(String message) {
        super(message);
    }

    public NullIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullIDException(Throwable cause) {
        super(cause);
    }
}
