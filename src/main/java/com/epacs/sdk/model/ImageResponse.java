package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.RequestException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ImageResponse {
    private Response response;
    private Map<String, Double> results;

    public ImageResponse(){
        results = new HashMap<>();
    }

    public ImageResponse(Response response, Map<String, Double> results){
        setResponse(response);
        setResults(results);
    }

    private void setResponse(Response response) {
        this.response = response;
    }

    public static ImageResponse parse(String jsonStr) throws RequestException, InternalException {
        Response response =  Response.parse(jsonStr);
        Map<String, Double> results = new HashMap<>();
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        JSONArray arrayResults = jsonObj.getJSONArray(ResponseKey.imageResultsKey);
        Iterator<Object> iter = arrayResults.iterator();
        while(iter.hasNext()) {
            JSONObject obj = (JSONObject) iter.next();
            results.put(obj.getString(ResponseKey.resultsNameKey),
                    obj.getDouble(ResponseKey.resultsScoreKey));

        }
        return new ImageResponse(response, results);
    }

    public Map getResults() {
        return results;
    }

    public void setResults(Map<String, Double> results) {
        this.results = results;
    }


    public int getLogId() {
        return response.getLogId();
    }

    public void setLogId(int logId) {
        this.response.setLogId(logId);
    }

    public int getErrorCode() {
        return response.getErrorCode();
    }

    public void setErrorCode(int errorCode) {
        this.response.setErrorCode(errorCode);
    }

    public String getErrorMsg() {
        return response.getErrorMsg();
    }

    public void setErrorMsg(String errorMsg) {
        this.response.setErrorMsg(errorMsg);
    }


}
