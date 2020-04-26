package com.epacs.sdk.common;

/**
 * @program: epacs-sdk
 * @package: com.epacs.sdk.common
 * @filename: ErrorCode.java
 * @create: 2020.04.13 18:24
 * @author: Kevin
 * @description: . 错误码
 **/
public enum ErrorCode{
    SUCCESS(200),
    ACCEPT(202),
    ERRORR_EQUEST(400),
    UNAUTHORIZED(401),
    NONE_EXIST(404),
    CONDITIION_INVALID(412),
    LARGE_REQUEST(413),
    INTREVAL_ERROR(500);
    // ---------------------------------------------------
    private int errorCode;

    ErrorCode(int errorCode){
        this.errorCode=errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public void setErrorCode(Integer errorCode){
        this.errorCode=errorCode;
    }
}
