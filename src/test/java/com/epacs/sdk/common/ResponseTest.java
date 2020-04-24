package com.epacs.sdk.common;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest{
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    String errJsonStr = "{\"log_id\":\"\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    Response response;
    Response errResponse;


    @Test
    public void testConstructor()  {
        try {
            assertNotNull(new Response(jsonStr));
        } catch (ResponseException e) {
            fail("");
        }
    }

    @Test
    public void testConstructorEx() {
        try {
            new Response(errJsonStr);
        } catch (ResponseException e) {
            ;
        }
    }


    @Test
    public void testGetLogId(){
        try {
            response = new Response(jsonStr);
        } catch (ResponseException e) {
            fail(e);
        }
        assertEquals(new Integer(1), response.getLogId());
    }



    @Test
    public void testGetErrorCode(){
        try {
            response = new Response(jsonStr);
        } catch (ResponseException e) {
            fail(e);
        }
        assertEquals(new Integer(200), response.getErrorCode());
    }

    @Test
    public void testGetErrorMsg(){
        try {
            response = new Response(jsonStr);
        } catch (ResponseException e) {
            fail(e);
        }
        assertEquals("hello", response.getErrorMsg());
    }
}
