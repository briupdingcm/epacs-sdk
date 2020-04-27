package com.epacs.sdk.model;

import com.epacs.sdk.utils.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecognitionResult implements Serializable{
    // 唯⼀的id，⽤于问题定位
    private Integer logId;
    // 错误码，当请求错误时返回
    private Integer errorCode;
    // 错误信息描述
    private String errorMsg;
    private List<Result> returnResults;

    public RecognitionResult(){}

    public RecognitionResult(Integer logId,Integer errorCode,String errorMsg){
        this.logId=logId;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

    public RecognitionResult(Integer logId,Integer errorCode,String errorMsg,List<Result> returnResults){
        this.logId=logId;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
        this.returnResults=returnResults;
    }

    public Integer getLogId(){
        return logId;
    }

    public void setLogId(Integer logId){
        this.logId=logId;
    }

    public Integer getErrorCode(){
        return errorCode;
    }

    public void setErrorCode(Integer errorCode){
        this.errorCode=errorCode;
    }

    public String getErrorMsg(){
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg){
        this.errorMsg=errorMsg;
    }

    public List<Result> getReturnResults(){
        return returnResults;
    }

    public void setReturnResults(List<Result> returnResults){
        this.returnResults=new ArrayList<Result>();
        this.returnResults.addAll(returnResults);
    }

    @Override
    public String toString(){
        return StringUtils.getInstance().toString(this);
    }
}
