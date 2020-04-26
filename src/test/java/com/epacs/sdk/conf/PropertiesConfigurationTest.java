package com.epacs.sdk.conf;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesConfigurationTest{
    InputStream is;
    Configuration conf;

    @BeforeEach
    public void start() throws Exception {
        is = PropertiesConfigurationTest.class.getResourceAsStream("/epacs.properties");
        Properties properties = new Properties();
        properties.load(is);
        conf  = new PropertiesConfiguration(properties);
    }

    @AfterEach
    public void stop() throws Exception {
        if (is != null)is.close();
    }


    @Test
    public void getAppPoint() {
        URI appPoint = URI.create("http://localhost/product");
        Configuration conf = new PropertiesConfiguration();
        conf.setAppPoint("http://localhost/product");
        assertEquals(appPoint, conf.getAppPoint());
    }

    @Test
    public void getTasksPoint() {
        Configuration conf = new PropertiesConfiguration();
        conf.setAppPoint("http://localhost/product");
        conf.setTasksPoint("/api/tasks");
        URI tasksPoint = URI.create("http://localhost/product/api/tasks");
        assertEquals(tasksPoint, conf.getTasksPoint());
    }

    @Test
    public void getImagesPoint() {
        Configuration conf = new PropertiesConfiguration();
        conf.setAppPoint("http://localhost/product");
        conf.setImagesPoint("/api/images");
        URI imagesPoint = URI.create("http://localhost/product/api/images");
        assertEquals(imagesPoint, conf.getImagesPoint());
    }


}
