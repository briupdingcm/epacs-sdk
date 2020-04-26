package com.epacs.sdk.common;

public class TaskException extends RuntimeException {
    private Integer errorCode;

    public TaskException(String errorMsg) {
        super(errorMsg);
    }

    public TaskException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
