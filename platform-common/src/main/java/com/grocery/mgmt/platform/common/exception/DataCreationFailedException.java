package com.grocery.mgmt.platform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class DataCreationFailedException extends RuntimeException {

    private static final long serialVersionUID = 2981138196909431621L;

    public DataCreationFailedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DataCreationFailedException(String arg0) {
        super(arg0);
    }

    public DataCreationFailedException(Throwable arg0) {
        super(arg0);
    }
}

