package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ParameterMissingException extends RuntimeException {

    private static final long serialVersionUID = 2991138196919431656L;

    public ParameterMissingException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ParameterMissingException(String message) {
        super(message);
    }

    public ParameterMissingException(Throwable throwable) {
        super(throwable);
    }
}
