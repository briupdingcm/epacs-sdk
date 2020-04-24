package com.epacs.sdk.conf;

import com.epacs.sdk.conf.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class PropertiesConfiguration implements Configuration {
    private static String appPointName = "api_point";
    private static String taskPointName = "task_url";
    private static String imagePointName = "image_url";
    private static String userName = "user_name";
    private static String password = "password";
    private static String time = "delay_ms";

    private Properties props;
    private URI appPoint;
    private URI tasksPoint;
    private URI imagesPoint;

    public PropertiesConfiguration() {
    }

    public PropertiesConfiguration(InputStream is) throws IOException {
        props = new Properties();
        props.load(is);
        appPoint = URI.create(props.getProperty(appPointName));
        tasksPoint = URI.create(props.getProperty(taskPointName));
        imagesPoint = URI.create(props.getProperty(imagePointName));
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
    public void setImagesPoint(URI imagesPoint) {
        this.imagesPoint = imagesPoint;
    }

    @Override
    public URI getImagesPoint() {
        return this.imagesPoint;
    }

    @Override
    public Long getWaitTime() {
        return Long.parseLong(props.getProperty(time));
    }

    @Override
    public String getUserName() {
        return props.getProperty(userName);
    }

    @Override
    public String getPassword() {
        return props.getProperty(password);
    }

    @Override
    public void setTasksPoint(String s) {
        tasksPoint = URI.create(s);
    }

}
