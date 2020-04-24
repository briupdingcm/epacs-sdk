package com.epacs.sdk.common;

public class RequestException extends Exception{
    private Integer code;
    private String msg;

    public RequestException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
