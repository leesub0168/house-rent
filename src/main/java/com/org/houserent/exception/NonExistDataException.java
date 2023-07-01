package com.org.houserent.exception;

public class NonExistDataException extends RuntimeException {
    public NonExistDataException() {
        super();
    }

    public NonExistDataException(String message) {
        super(message);
    }

    public NonExistDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistDataException(Throwable cause) {
        super(cause);
    }

    protected NonExistDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
