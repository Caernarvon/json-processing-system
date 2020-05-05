package com.vovk.jsonprocessingsystem.model.exceptions;

/**
 * @author Dmytro Vovk
 */

public class BadFileFormatException extends RuntimeException {

    public BadFileFormatException() {
    }

    public BadFileFormatException(String message) {
        super(message);
    }

    public BadFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFileFormatException(Throwable cause) {
        super(cause);
    }

    public BadFileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
