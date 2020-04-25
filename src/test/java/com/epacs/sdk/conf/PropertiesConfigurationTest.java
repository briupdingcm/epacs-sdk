package com.epacs.sdk.conf;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
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
    @Before
    @BeforeEach
    public void start() throws Exception {
        is = PropertiesConfigurationTest.class.getResourceAsStream("/epacs.properties");
        Properties properties = new Properties();
        properties.load(is);
        conf  = new PropertiesConfiguration(properties);
    }

    @After
    public void stop() throws Exception {
        if (is != null)is.close();
    }


    @Test
    public void testGetAppPoint() {
        URI appPoint = URI.create("/api");
        conf.setAppPoint("/api");
        assertEquals(appPoint, conf.getAppPoint());
    }

    @Test
    public void testGetAppPointNotSame() {
        URI appPoint = URI.create("");
        assertNotSame(appPoint, conf.getAppPoint());
    }

    @Test
    public void testGetAppPointNotNull(){
        assertNotNull(conf.getAppPoint());
    }

    @Test
    public void testGetTasksPoint() {
        URI tasksPoint = URI.create("/api/tasks");
        assertEquals(tasksPoint, conf.getTasksPoint());
    }

    @Test
    public void testGetTasksPointNotNull() {
        assertNotNull(conf.getTasksPoint());
    }

    @Test
    public void testGetTasksPointNotSame() {
        URI tasksPoint = URI.create("");
        assertNotSame(tasksPoint, conf.getTasksPoint());
    }

    @Test
    public void testGetImagesPoint() {
        URI imagesPoint = URI.create("/api/images");
        conf.setImagesPoint("/api/images");
        assertEquals(imagesPoint, conf.getImagesPoint());
    }

    @Test
    public void testGetImagesPointNotNull() {
        assertNotNull(conf.getImagesPoint());
    }

    @Test
    public void testGetImagesPointNotSame() {
        URI imagesPoint = URI.create("");
        assertNotSame(imagesPoint, conf.getImagesPoint());
    }
}
