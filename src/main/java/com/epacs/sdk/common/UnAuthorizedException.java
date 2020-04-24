package com.epacs.sdk.common;

public class UnAuthorizedException extends Exception{
    private Integer code;
    private String msg;

    public UnAuthorizedException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
