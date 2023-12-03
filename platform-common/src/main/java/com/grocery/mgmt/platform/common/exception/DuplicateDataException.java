package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class DuplicateDataException extends RuntimeException {

    private static final long serialVersionUID = 2981138196909431621L;

    public DuplicateDataException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicateDataException(String arg0) {
        super(arg0);
    }

    public DuplicateDataException(Throwable arg0) {
        super(arg0);
    }
}

