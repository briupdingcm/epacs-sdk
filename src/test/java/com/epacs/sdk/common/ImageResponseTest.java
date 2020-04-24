package com.epacs.sdk.common;

import com.epacs.sdk.model.ImageResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageResponseTest{
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
            "\"results\":[{\"name\":\"qingwei\",\"score\":\"0.1\"}, {\"name\":\"qingdu\",\"score\":\"0.9\"}]}";

    ImageResponse ir;

    @Before
    public void createImageResponse() throws ResponseException {
        ir = new ImageResponse(jsonStr);
    }

    @After
    public void stop(){
        ir = null;
    }

    @Test
    public void testImageResponse() throws ResponseException {
        ir = new ImageResponse(jsonStr);
        assertNotNull(ir);
    }

    @Test
    public void testGetResultsNotNull(){
        assertNotNull(ir.getResults());
    }

    @Test
    public void testGetResults(){
        assertEquals(2, ir.getResults().size());
    }
}
