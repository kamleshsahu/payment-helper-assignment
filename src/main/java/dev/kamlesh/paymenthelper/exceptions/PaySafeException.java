package dev.kamlesh.paymenthelper.exceptions;

public class PaySafeException extends Exception {
    public PaySafeException() {
        super();
    }

    public PaySafeException(String message) {
        super(message);
    }

    public PaySafeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaySafeException(Throwable cause) {
        super(cause);
    }
}
