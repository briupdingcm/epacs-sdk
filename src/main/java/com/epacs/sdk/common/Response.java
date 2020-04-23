package com.epacs.sdk.common;

import com.alibaba.fastjson.JSONObject;

public class Response {
    private Integer logId;
    private Integer errorCode;
    private String errorMsg;

    public Response(String jsonStr){
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        logId = jsonObj.getInteger(ResponseKey.logIdKey);
        errorCode = jsonObj.getInteger(ResponseKey.errorCodeKey);
        errorMsg = jsonObj.getString(ResponseKey.errorMsgKey);
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
