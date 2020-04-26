package com.epacs.sdk.recognition;

import com.epacs.sdk.common.ImageFormatException;
import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.RequestException;
import com.epacs.sdk.common.ResponseException;
import com.epacs.sdk.model.ImageResponse;
import com.epacs.sdk.model.TaskResponse;
import com.epacs.sdk.conf.Configuration;
import com.epacs.sdk.conf.PropertiesConfiguration;
//import okhttp3.Dispatcher;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageProcessorTest {
    final String resp1 = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
            "\"task_id\":\"22\", \"created_by\":\"kevin\", \"status\":\"SUCCESS\", \"image_id\":\"2222\"}";
    final String resp2 = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", \"region\":\"NOZZLE\", " +
            "\"results\":[{\"name\":\"hsd1qingwei\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd2qingdu\",\"score\":\"0.5\"}, " +
            "{\"name\":\"hsd3zhongdu\",\"score\":\"0.2\"}, " +
            "{\"name\":\"hsd4zhongdu\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd5yanzhong\",\"score\":\"0.1\"}, " +
            "]}";
    int resultCount = 5;
    Configuration conf;
    String token = "";
    MockWebServer server;
    @Before
    public void start() throws IOException {
        final Dispatcher dispatcher = new Dispatcher() {

            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                System.out.println(recordedRequest.getPath());
                if (recordedRequest.getPath().equals("/api/tasks")){
                    return new MockResponse().setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(resp1);
                } else if (recordedRequest.getPath().equals("/api/tasks/22")){
                    return new MockResponse().setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(resp1);
                } else if (recordedRequest.getPath().equals("/api/images/2222?query=result")) {
                    return new MockResponse().setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(resp2);
                } else {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        server = new MockWebServer();
        server.setDispatcher(dispatcher);
        //server.url("/product");
        server.start();

        conf = new PropertiesConfiguration();
        conf.setAppPoint("http://" + server.getHostName() + ":" + server.getPort());
        conf.setTasksPoint("/api/tasks");
        conf.setImagesPoint("/api/images");

    }

    @After
    public void stop() throws IOException {
        server.shutdown();
    }

    @Test
    public void createTask() throws IOException, InternalException, InterruptedException {
        String image = "";

        ImageProcessor ip = new ImageProcessor(token, conf);

        TaskResponse taskResponse = null;
        try {
            taskResponse = ip.createTask(image);
        } catch (RequestException e) {
            fail(e.getMessage());
        }
        assertNotNull(taskResponse);
        RecordedRequest rr = server.takeRequest();
        assertEquals("POST", rr.getMethod().toUpperCase());

    }

    @Test
    public void getTaskInfo() throws InterruptedException {
        ImageProcessor ip = new ImageProcessor(token, conf);
        TaskResponse taskResponse = null;
        try {
            taskResponse = ip.getTaskInfo(22);
        } catch (RequestException | InternalException | IOException e) {
            fail(e.getMessage());
        }
        assertNotNull(taskResponse);
        RecordedRequest rr = server.takeRequest();
        assertEquals("GET", rr.getMethod().toUpperCase());

    }

    @Test
    public void getImageInfo() throws InterruptedException {
        ImageProcessor ip = new ImageProcessor(token, conf);

        ImageResponse imageResponse = null;
        try {
            imageResponse = ip.getImageInfo("2222");
        } catch (RequestException | InternalException | IOException e) {
            fail(e.getMessage());
        }
        assertNotNull(imageResponse);
        RecordedRequest rr = server.takeRequest();
        assertEquals("GET", rr.getMethod().toUpperCase());

    }

    @Test
    public void getResult(){
        ImageProcessor ip = new ImageProcessor(token, conf);

        ImageResponse results = null;
        try {
            results = ip.getResult(22);
        } catch (RequestException | InternalException | IOException e) {
            fail(e.getMessage());
        }
        assertEquals(resultCount, results.getResults().size());
    }


    @Test
    public void submit(){
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        ImageResponse results = null;
        try {
            results = ip.submit(image);
        } catch (RequestException | InternalException | IOException  e) {
            fail(e.getMessage());
        }
        assertEquals(resultCount, results.getResults().size());
    }

    @Test
    public void submitAsync(){
        ImageProcessor ip = new ImageProcessor(token, conf);

        String image = "";
        try {
            ip.submit(image, new ResultCallback() {
                @Override
                public void callback(ImageResponse imageResponse) {
                    assertEquals(resultCount, imageResponse.getResults().size());
                }
            });
        } catch (RequestException | InternalException | IOException  e) {
            fail(e.getMessage());
        }
    }
}
