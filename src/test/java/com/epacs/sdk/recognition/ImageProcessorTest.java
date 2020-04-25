package com.epacs.sdk.recognition;

import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.RequestException;
import com.epacs.sdk.common.ResponseException;
import com.epacs.sdk.model.ImageResponse;
import com.epacs.sdk.model.TaskResponse;
import com.epacs.sdk.conf.Configuration;
import com.epacs.sdk.conf.PropertiesConfiguration;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageProcessorTest {
    Configuration conf;
    ImageProcessor ip;
    String token = "";
    @Before
    public void start(){
        conf = new PropertiesConfiguration();
        ip = new ImageProcessor("", conf);
    }
    @Test
    public void createTask() throws IOException, InternalException, ResponseException {
        String resp = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"task_id\":\"2\", \"created_by\":\"kevin\", \"status\":\"SUCCESS\", \"image_id\":\"2222\"}";
        MockWebServer server = new MockWebServer();
        MockResponse mr = new MockResponse();
        mr.setHeader("Content-Type", "application/json");
        mr.setHeader("Authorization", token);
        mr.setBody(resp);
        server.enqueue(mr);
        server.start();
        conf.setTasksPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/tasks");
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        TaskResponse taskResponse = null;
        try {
            taskResponse = ip.createTask(image);
        } catch (RequestException e) {
            fail("");
        }
        assertNotNull(taskResponse);
    }

    @Test
    public void getTaskInfo(){
        String resp = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"task_id\":\"2\", \"created_by\":\"kevin\", \"status\":\"SUCCESS\", \"image_id\":\"2222\"}";
        MockWebServer server = new MockWebServer();
        MockResponse mr = new MockResponse();
        mr.setHeader("Content-Type", "application/json");
        mr.setHeader("Authorization", token);
        mr.setBody(resp);
        server.enqueue(mr);
        //server.start();
        conf.setTasksPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/tasks");

        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        TaskResponse taskResponse = null;
        try {
            taskResponse = ip.getTaskInfo(2);
        } catch (RequestException | InternalException | IOException e) {
            e.printStackTrace();
            fail("");
        }
        assertNotNull(taskResponse);
    }

    @Test
    public void getImageInfo(){
        String resp = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"results\":[{\"name\":\"qingwei\",\"score\":\"0.1\"}, {\"name\":\"qingdu\",\"score\":\"0.9\"}]}";
        MockWebServer server = new MockWebServer();
        MockResponse mr = new MockResponse();
        mr.setHeader("Content-Type", "application/json");
        mr.setHeader("Authorization", token);
        mr.setBody(resp);
        server.enqueue(mr);
        //server.start();

        conf.setImagesPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/images");
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        ImageResponse imageResponse = null;
        try {
            imageResponse = ip.getImageInfo("2222");
        } catch (RequestException | InternalException | IOException e) {
            e.printStackTrace();
            fail("");
        }
        assertNotNull(imageResponse);
    }

    @Test
    public void getResult(){
        String resp1 = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"task_id\":\"2\", \"created_by\":\"kevin\", \"status\":\"SUCCESS\", \"image_id\":\"2222\"}";
        String resp2 = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"results\":[{\"name\":\"qingwei\",\"score\":\"0.1\"}, {\"name\":\"qingdu\",\"score\":\"0.9\"}]}";
        MockWebServer server = new MockWebServer();
        MockResponse mr1 = new MockResponse();
        mr1.setHeader("Content-Type", "application/json");
        mr1.setHeader("Authorization", token);
        mr1.setBody(resp1);
        MockResponse mr2 = new MockResponse();
        mr2.setHeader("Content-Type", "application/json");
        mr2.setHeader("Authorization", token);
        mr2.setBody(resp2);

        server.enqueue(mr1);
        server.enqueue(mr2);
//        server.start();

        conf.setTasksPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/tasks");
        conf.setImagesPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/images");
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        Map<String, Double > results = null;
        try {
            results = ip.getResult(2);
        } catch (RequestException | InternalException | IOException e) {
            fail(e.getMessage());
        }
        assertEquals(2, results.size());
    }
}
