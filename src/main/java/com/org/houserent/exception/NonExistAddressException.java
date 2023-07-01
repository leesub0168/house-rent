package com.org.houserent.exception;

public class NonExistAddressException extends RuntimeException{
    public NonExistAddressException() {
        super();
    }

    public NonExistAddressException(String message) {
        super(message);
    }

    public NonExistAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistAddressException(Throwable cause) {
        super(cause);
    }

    protected NonExistAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
