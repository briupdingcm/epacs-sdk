package com.epacs.sdk.recognition;

import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.ResponseException;
import com.epacs.sdk.common.TaskResponse;
import com.epacs.sdk.common.UnAuthorizedException;
import com.epacs.sdk.conf.Configuration;
import com.epacs.sdk.conf.PropertiesConfiguration;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

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
    public void testCreateTask() throws IOException, UnAuthorizedException, InternalException, ResponseException {
        String resp = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"task_id\":\"2\", \"created_by\":\"kevin\", \"status\":\"SUCCESS\", \"image_id\":\"2222\"}";
        MockWebServer server = new MockWebServer();
        MockResponse mr = new MockResponse();
        mr.setHeader("Content-Type", "application/json");
        mr.setHeader("Authorization", token);
        mr.setBody(resp);
        server.enqueue(mr);

        conf.setTasksPoint("http://" + server.getHostName() + ":" + server.getPort() + "/api/tasks");
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        TaskResponse taskResponse = ip.createTask(image);
        assertNotNull(taskResponse);
    }
}
