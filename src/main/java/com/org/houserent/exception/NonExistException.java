package com.org.houserent.exception;

public class NonExistException extends RuntimeException {
    public NonExistException() {
        super();
    }

    public NonExistException(String message) {
        super(message);
    }

    public NonExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistException(Throwable cause) {
        super(cause);
    }

    protected NonExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
