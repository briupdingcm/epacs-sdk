package com.epacs.sdk.model;

import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.Region;
import com.epacs.sdk.common.RequestException;
import com.epacs.sdk.model.ImageResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class ImageResponseTest{
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", \"region\":\"NOZZLE\", " +
            "\"results\":[{\"name\":\"hsd1qingwei\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd2qingdu\",\"score\":\"0.5\"}, " +
            "{\"name\":\"hsd3zhongdu\",\"score\":\"0.2\"}, " +
            "{\"name\":\"hsd4zhongdu\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd5yanzhong\",\"score\":\"0.1\"}, " +
            "]}";
    String errJsonStr = "{\"log_id\":\"1\", \"{error_code\":\"200\", \"error_msg\":\"hello\", \"region\":\"NOZZLE\", " +
            "\"results\":[{\"name\":\"hsd1qingwei\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd2qingdu\",\"score\":\"0.5\"}, " +
            "{\"name\":\"hsd3zhongdu\",\"score\":\"0.2\"}, " +
            "{\"name\":\"hsd4zhongdu\",\"score\":\"0.1\"}, " +
            "{\"name\":\"hsd5yanzhong\",\"score\":\"0.1\"}, " +
            "]}";

    int resultCount = 5;
    ImageResponse ir;

    @Before
    public void start() {
        try {
            ir =  ImageResponse.parse(jsonStr);
            assertNotNull(ir);
        } catch (RequestException | InternalException e) {
            fail(e);
        }
    }

    @After
    public void stop(){
        ir = null;
    }

    @Test
    public void parse() {
        try {
            ir = ImageResponse.parse(jsonStr);
            assertNotNull(ir);
        } catch (RequestException | InternalException e) {
            fail(e);
        }
    }

    @Test(expected = InternalException.class)
    public void parseReqEx() throws InternalException, RequestException {
        ir = ImageResponse.parse(errJsonStr);
        assertNull(ir);
    }

    @Test
    public void getResultsNotNull(){
        assertNotNull(ir.getResults());
    }

    @Test
    public void getResults(){
        assertEquals(resultCount, ir.getResults().size());
    }

    @Test
    public void getRegion(){
        Assertions.assertEquals(Region.NOZZLE, ir.getRegion());
    }
}
