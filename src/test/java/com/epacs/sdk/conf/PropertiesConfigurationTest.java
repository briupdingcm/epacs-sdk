package com.epacs.sdk.conf;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class PropertiesConfigurationTest extends TestCase {
    InputStream is;
    Configuration conf;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        is = PropertiesConfigurationTest.class.getResourceAsStream("/epacs.properties");
        Properties properties = new Properties();
        properties.load(is);
        conf  = new PropertiesConfiguration(properties);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if (is != null)is.close();
    }



    public void testGetAppPoint() {
        URI appPoint = URI.create("/api");
        assertEquals(appPoint, conf.getAppPoint());
    }

    public void testGetAppPointNotSame() {
        URI appPoint = URI.create("");
        assertNotSame(appPoint, conf.getAppPoint());
    }

    public void testGetAppPointNotNull(){
        assertNotNull(conf.getAppPoint());
    }

    public void testGetTasksPoint() {
        URI tasksPoint = URI.create("/api/tasks");
        assertEquals(tasksPoint, conf.getTasksPoint());
    }

    public void testGetTasksPointNotNull() {
        assertNotNull(conf.getTasksPoint());
    }

    public void testGetTasksPointNotSame() {
        URI tasksPoint = URI.create("");
        assertNotSame(tasksPoint, conf.getTasksPoint());
    }

    public void testGetImagesPoint() {
        URI imagesPoint = URI.create("/api/images");
        assertEquals(imagesPoint, conf.getImagesPoint());
    }

    public void testGetImagesPointNotNull() {
        assertNotNull(conf.getImagesPoint());
    }

    public void testGetImagesPointNotSame() {
        URI imagesPoint = URI.create("");
        assertNotSame(imagesPoint, conf.getImagesPoint());
    }
}
