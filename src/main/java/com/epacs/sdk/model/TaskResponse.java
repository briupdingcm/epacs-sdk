package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.RequestException;
import com.epacs.sdk.common.TaskStatus;

/**
 * 任务信息
 *
 * @create: 2020.04.13 18:24
 * @author: Kevin
 */
public class TaskResponse {
    private Response response;
    private int taskId; //任务编号
    private String createdBy; //任务创建者
    private TaskStatus status; //任务的状态
    private String imageId; //任务处理图像的id


    public TaskResponse() {
    }

    public TaskResponse(Response response, int taskId, String createdBy,
                        TaskStatus status, String imageId) {
        setResponse(response);
        setTaskId(taskId);
        setCreatedBy(createdBy);
        setStatus(status);
        setImageId(imageId);
    }

    public static TaskResponse parse(String jsonStr) throws RequestException, InternalException {
        Response response = Response.parse(jsonStr);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        int taskId = jsonObj.getInteger(ResponseKey.TASK_ID_KEY);
        String createdBy = jsonObj.getString(ResponseKey.CREATED_BY_KEY);
        TaskStatus status = TaskStatus.fromString(jsonObj.getString(ResponseKey.STATUS_KEY));
        String imageId =jsonObj.getString(ResponseKey.IMAGE_ID_KEY);
        return new TaskResponse(response, taskId, createdBy, status, imageId);
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


    public int getLogId() {
        return response.getLogId();
    }

    public void setLogId(int logId) {
        this.response.setLogId(logId);
    }

    public int getErrorCode() {
        return response.getErrorCode();
    }

    public void setErrorCode(int errorCode) {
        this.response.setErrorCode(errorCode);
    }

    public String getErrorMsg() {
        return response.getErrorMsg();
    }

    public void setErrorMsg(String errorMsg) {
        this.response.setErrorMsg(errorMsg);
    }
}
