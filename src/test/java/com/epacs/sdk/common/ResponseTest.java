package com.epacs.sdk.common;

import junit.framework.TestCase;

public class ResponseTest extends TestCase {
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    String errJsonStr = "{\"log_id\":\"\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    Response response;
    Response errResponse;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        response = new Response(jsonStr);
        errResponse = new Response(errJsonStr);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testResponse() throws ResponseException {
        Response response = new Response(jsonStr);
        assertNotNull(response);
    }

    public void testGetLogId(){
        assertEquals(new Integer(1), response.getLogId());
    }

    public void testGetLogIdNull(){
        assertNull(errResponse.getLogId());
    }

    public void testGetErrorCode(){
        assertEquals(new Integer(200), response.getErrorCode());
    }

    public void testGetErrorMsg(){
        assertEquals("hello", response.getErrorMsg());
    }
}
