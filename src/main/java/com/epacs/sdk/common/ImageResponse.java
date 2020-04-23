package com.epacs.sdk.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ImageResponse {
    private Response response;
    private Map results = new HashMap<>();

    public ImageResponse(String jsonStr){
        response = new Response(jsonStr);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        JSONArray arrayResults = jsonObj.getJSONArray(ResponseKey.imageResultsKey);
        Iterator<Object> iter = arrayResults.iterator();
        while(iter.hasNext()) {
            JSONObject obj = (JSONObject) iter.next();
            results.put(obj.getString(ResponseKey.resultsNameKey),
                    obj.getDouble(ResponseKey.resultsScoreKey));

        }
    }

    public Map getResults() {
        return results;
    }

    public void setResults(Map results) {
        this.results = results;
    }


    public Integer getLogId() {
        return response.getLogId();
    }

    public void setLogId(Integer logId) {
        this.response.setLogId(logId);
    }

    public Integer getErrorCode() {
        return response.getErrorCode();
    }

    public void setErrorCode(Integer errorCode) {
        this.response.setErrorCode(errorCode);
    }

    public String getErrorMsg() {
        return response.getErrorMsg();
    }

    public void setErrorMsg(String errorMsg) {
        this.response.setErrorMsg(errorMsg);
    }

    public static void main(String... args){
        String jsonStr = "{\"log_id\":\"1\", \"error_code\":\"200\", \"error_msg\":\"hello\", " +
                "\"results\":[{\"name\":\"qingwei\",\"score\":\"0.1\"}, {\"name\":\"qingdu\",\"score\":\"0.9\"}]}";
        ImageResponse ir = new ImageResponse(jsonStr);
    }
}
