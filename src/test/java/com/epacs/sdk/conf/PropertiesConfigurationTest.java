package com.epacs.sdk.conf;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class PropertiesConfigurationTest extends TestCase {
    InputStream is;
    Configuration conf;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        is = PropertiesConfigurationTest.class.getResourceAsStream("/epacs.properties");
        Properties properties = new Properties();
        properties.load(is);
        conf  = new PropertiesConfiguration(properties);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        if (is != null)is.close();
    }


    @Test
    public void testGetAppPoint() {
        URI appPoint = URI.create("/api");
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
