package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.ResponseException;

public class Response {
    private Integer logId;
    private Integer errorCode;
    private String errorMsg;

    public Response(String jsonStr) throws ResponseException {
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        logId = jsonObj.getInteger(ResponseKey.logIdKey);
        if (logId == null)throw new ResponseException("logId can not be empty");
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
