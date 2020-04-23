package com.epacs.sdk.common;

import junit.framework.TestCase;

public class ResponseTest extends TestCase {
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\"}"; //, ";  +

    String errJsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\"}"; //, " +

    Response response;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        response = new Response(jsonStr);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testResponse() {
        Response response = new Response(jsonStr);
        assertNotNull(response);
    }

    public void testResponseNull(){
        Response response = new Response(errJsonStr);
        assertNull(response);
    }
}
