package com.org.houserent.exception;

public class NonExistMemberException extends RuntimeException {
    public NonExistMemberException() {
        super();
    }

    public NonExistMemberException(String message) {
        super(message);
    }

    public NonExistMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistMemberException(Throwable cause) {
        super(cause);
    }

    protected NonExistMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
