package com.epacs.sdk.conf;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class PropertiesConfiguration implements Configuration {
    private static final String APP_POINT_KEY = "api_point";
    private static final String TASK_POINT_KEY = "task_url";
    private static final String IMAGE_POINT_KEY = "image_url";
    private static final String TIME_KEY = "delay_ms";
    //应用系统顶级访问点
    private String appPoint;
    //任务资源访问点
    private String tasksPoint;
    //图像资源访问点
    private String imagesPoint;
    //轮询时间间隔
    private long waitTime;

    public PropertiesConfiguration() {
    }

    public PropertiesConfiguration(Properties props) throws IOException {
        appPoint = props.getProperty(APP_POINT_KEY);
        tasksPoint = props.getProperty(TASK_POINT_KEY);
        imagesPoint = props.getProperty(IMAGE_POINT_KEY);
        waitTime = Long.parseLong(props.getProperty(TIME_KEY));
    }
    @Override
    public URI getAppPoint() {
        return URI.create(appPoint);
    }

    @Override
    public void setAppPoint(String appPoint) {
        this.appPoint = appPoint;
    }

    @Override
    public URI getTasksPoint() {
        return URI.create(appPoint + tasksPoint);
    }

    @Override
    public void setTasksPoint(String tasksPointStr) {
        this.tasksPoint = tasksPointStr;
    }

    @Override
    public void setImagesPoint(String imagesPoint) {
        this.imagesPoint = imagesPoint;
    }

    @Override
    public URI getImagesPoint() {
        return URI.create(this.appPoint + this.imagesPoint);
    }

    @Override
    public long getWaitTime() {
        return waitTime;
    }



}
