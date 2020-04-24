package com.epacs.sdk.common;

import com.epacs.sdk.model.Response;
import org.junit.jupiter.api.Test;

import static com.epacs.sdk.model.Response.parse;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest{
    String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    String errJsonStr = "{\"log_id\":\"\", \"error_code\":\"200\", \"error_msg\":\"hello\"}";

    Response response;
    Response errResponse;


    @Test
    public void testParse()  {
        try {
            assertNotNull(parse(jsonStr));
        } catch (InternalException | RequestException e) {
            fail("");
        }
    }

    @Test
    public void testParseEx(){
        try {
            Response.parse(errJsonStr);
        }catch(RequestException | InternalException e){

        }
    }


    @Test
    public void testGetLogId(){
        try {
            response = parse(jsonStr);
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        assertEquals(1, response.getLogId());
    }



    @Test
    public void testGetErrorCode(){
        try {
            response = Response.parse(jsonStr);
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        assertEquals(200, response.getErrorCode());
    }

    @Test
    public void testGetErrorMsg(){
        try {
            response = Response.parse(jsonStr);
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        assertEquals("hello", response.getErrorMsg());
    }
}
