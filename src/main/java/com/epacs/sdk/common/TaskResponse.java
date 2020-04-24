package com.epacs.sdk.common;

import com.alibaba.fastjson.JSONObject;

public class TaskResponse {
    private Response response;
    private Integer    taskId;
    private String  createdBy;
    private TaskStatus status;
    private Integer        imageId;

    public TaskResponse(String jsonStr) throws ResponseException {
        response = new Response(jsonStr);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        taskId = jsonObj.getInteger(ResponseKey.taskIdKey);
        createdBy = jsonObj.getString(ResponseKey.createdByKey);
        status = TaskStatus.fromString(jsonObj.getString(ResponseKey.statusKey));
        imageId =jsonObj.getInteger(ResponseKey.imageIdKey);
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }


    public Integer getLogId() {
        return response.getLogId();
    }

    public void setLogId(Integer logId) {
        this.response.setLogId(logId);
    }

    public Integer getErrorCode() {
        return response.getErrorCode();
    }

    public void setErrorCode(Integer errorCode) {
        this.response.setErrorCode(errorCode);
    }

    public String getErrorMsg() {
        return response.getErrorMsg();
    }

    public void setErrorMsg(String errorMsg) {
        this.response.setErrorMsg(errorMsg);
    }
}
