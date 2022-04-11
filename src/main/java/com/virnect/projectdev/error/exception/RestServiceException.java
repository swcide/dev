package com.virnect.projectdev.error.exception;

import com.virnect.projectdev.error.ErrorCode;

public class RestServiceException extends BusinessException {
    private final ErrorCode error;

    public RestServiceException(ErrorCode error) {
        super(error);
        this.error = error;
    }

    public ErrorCode getError() {
        return this.error;
    }
}
