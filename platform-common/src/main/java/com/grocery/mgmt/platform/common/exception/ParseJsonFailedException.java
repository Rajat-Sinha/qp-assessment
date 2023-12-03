package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParseJsonFailedException extends RuntimeException {

    private static final long serialVersionUID = 2901138196909431621L;

    public ParseJsonFailedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ParseJsonFailedException(String arg0) {
        super(arg0);
    }

    public ParseJsonFailedException(Throwable arg0) {
        super(arg0);
    }
}
