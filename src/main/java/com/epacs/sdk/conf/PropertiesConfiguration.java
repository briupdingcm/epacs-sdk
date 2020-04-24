package com.epacs.sdk.conf;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class PropertiesConfiguration implements Configuration {
    private static final String APP_POINT_KEY = "api_point";
    private static final String TASK_POINT_KEY = "task_url";
    private static final String IMAGE_POINT_KEY = "image_url";
    private static final String TIME_KEY = "delay_ms";

    private URI appPoint;
    private URI tasksPoint;
    private URI imagesPoint;
    private long waitTime;

    public PropertiesConfiguration() {
    }

    public PropertiesConfiguration(Properties props) throws IOException {
        appPoint = URI.create(props.getProperty(APP_POINT_KEY));
        tasksPoint = URI.create(props.getProperty(TASK_POINT_KEY));
        imagesPoint = URI.create(props.getProperty(IMAGE_POINT_KEY));
        waitTime = Long.parseLong(props.getProperty(TIME_KEY));
    }
    @Override
    public URI getAppPoint() {
        return appPoint;
    }

    @Override
    public void setAppPoint(URI appPoint) {
        this.appPoint = appPoint;
    }

    @Override
    public URI getTasksPoint() {
        return tasksPoint;
    }

    @Override
    public void setTasksPoint(URI tasksPoint) {
        this.tasksPoint = tasksPoint;
    }

    @Override
    public void setTasksPoint(String tasksPointStr) {
        tasksPoint = URI.create(tasksPointStr);
    }

    @Override
    public void setImagesPoint(URI imagesPoint) {
        this.imagesPoint = imagesPoint;
    }

    @Override
    public URI getImagesPoint() {
        return this.imagesPoint;
    }

    @Override
    public long getWaitTime() {
        return waitTime;
    }



}
