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
    public PropertiesConfiguration(InputStream is) throws IOException {
        props = new Properties();
        props.load(is);

    }
    @Override
    public URI getAppPoint() {
        return URI.create(props.getProperty(appPointName));
    }

    @Override
    public URI getTasksPoint() {
        return URI.create(props.getProperty(taskPointName));
    }

    @Override
    public URI getImagesPoint() {
        return URI.create(props.getProperty(imagePointName));
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
}
