package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.ErrorCode;
import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.RequestException;

public class Response {
    private int logId;
    private int errorCode;
    private String errorMsg;

    public Response(){}

    public Response(int logId, int errorCode, String errorMsg){
        setLogId(logId);
        setErrorCode(errorCode);
        setErrorMsg(errorMsg);
    }


    public static Response parse(String jsonStr) throws InternalException, RequestException {
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);

        // 获得响应码
        int errorCode = jsonObj.getIntValue(ResponseKey.ERROR_CODE_KEY);
        String errorMsg = jsonObj.getString(ResponseKey.ERROR_MSG_KEY);
        int logId = jsonObj.getIntValue(ResponseKey.LOG_ID_KEY);

        if (errorCode == ErrorCode.SUCCESS.getErrorCode() ||
                errorCode == ErrorCode.ACCEPT.getErrorCode()) {
            return new Response(logId, errorCode, errorMsg);
        } else if (errorCode == ErrorCode.CONDITIION_INVALID.getErrorCode() ||
                errorCode == ErrorCode.LARGE_REQUEST.getErrorCode() ||
                errorCode == ErrorCode.ERRORR_EQUEST.getErrorCode() ||
                errorCode == ErrorCode.NONE_EXIST.getErrorCode() ||
                errorCode == ErrorCode.UNAUTHORIZED.getErrorCode()) {
            throw new RequestException(errorCode, errorMsg);
        }else{
            throw new InternalException("server internal error");
        }
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
