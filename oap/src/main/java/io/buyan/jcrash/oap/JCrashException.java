package io.buyan.jcrash.oap;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/11
 */
public class JCrashException extends RuntimeException {

    public JCrashException() {
    }

    public JCrashException(String message) {
        super(message);
    }

    public JCrashException(String message, Throwable cause) {
        super(message, cause);
    }

    public JCrashException(Throwable cause) {
        super(cause);
    }

    public JCrashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
