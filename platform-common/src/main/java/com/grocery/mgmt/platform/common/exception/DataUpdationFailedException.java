package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class DataUpdationFailedException extends RuntimeException {

    private static final long serialVersionUID = 2981138196919431621L;

    public DataUpdationFailedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DataUpdationFailedException(String arg0) {
        super(arg0);
    }

    public DataUpdationFailedException(Throwable arg0) {
        super(arg0);
    }
}

