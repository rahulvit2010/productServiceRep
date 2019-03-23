package com.epam.productservice.exception;

public class NoContentFoundException extends RuntimeException {
    public NoContentFoundException(String message) {
        super(message);
    }

    public NoContentFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentFoundException(Throwable cause) {
        super(cause);
    }
}
