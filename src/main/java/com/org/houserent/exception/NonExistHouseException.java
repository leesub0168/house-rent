package com.org.houserent.exception;

public class NonExistHouseException extends RuntimeException {
    public NonExistHouseException() {
        super();
    }

    public NonExistHouseException(String message) {
        super(message);
    }

    public NonExistHouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistHouseException(Throwable cause) {
        super(cause);
    }

    protected NonExistHouseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
