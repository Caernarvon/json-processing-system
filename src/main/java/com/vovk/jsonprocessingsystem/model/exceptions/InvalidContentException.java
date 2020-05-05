package com.vovk.jsonprocessingsystem.model.exceptions;

/**
 * @author Dmytro Vovk
 */

public class InvalidContentException extends RuntimeException {

    public InvalidContentException() {
    }

    public InvalidContentException(String message) {
        super(message);
    }

    public InvalidContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidContentException(Throwable cause) {
        super(cause);
    }

    public InvalidContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
