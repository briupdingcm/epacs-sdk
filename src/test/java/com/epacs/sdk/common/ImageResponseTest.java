package com.epacs.sdk.common;

import com.epacs.sdk.model.ImageResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageResponseTest{
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
            "\"results\":[{\"name\":\"qingwei\",\"score\":\"0.1\"}, {\"name\":\"qingdu\",\"score\":\"0.9\"}]}";

    ImageResponse ir;

    @Before
    public void start() throws ResponseException {
        try {
            ir =  ImageResponse.parse(jsonStr);
            assertNotNull(ir);
        } catch (RequestException | InternalException e) {
            fail("");
        }
    }

    @After
    public void stop(){
        ir = null;
    }

    @Test
    public void parse() throws ResponseException {
        try {
            ir = ImageResponse.parse(jsonStr);
            assertNotNull(ir);
        } catch (RequestException | InternalException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getResultsNotNull(){
        assertNotNull(ir.getResults());
    }

    @Test
    public void getResults(){
        assertEquals(2, ir.getResults().size());
    }
}
