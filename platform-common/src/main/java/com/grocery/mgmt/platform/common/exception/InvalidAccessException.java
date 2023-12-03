package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAccessException extends RuntimeException {

    private static final long serialVersionUID = 2991131196919431656L;

    public InvalidAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidAccessException(String message) {
        super(message);
    }

    public InvalidAccessException(Throwable throwable) {
        super(throwable);
    }
}
