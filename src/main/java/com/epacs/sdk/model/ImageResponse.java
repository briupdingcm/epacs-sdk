package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.InternalException;
import com.epacs.sdk.common.Region;
import com.epacs.sdk.common.RequestException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ImageResponse {
    private Response response;
    private Region  region;
    private Map<String, Double> results;

    public ImageResponse(){
        results = new HashMap<>();
    }

    public ImageResponse(Response response, Region region, Map<String, Double> results){
        setResponse(response);
        setRegion(region);
        setResults(results);
    }

    /**
     * 解析json字符串，构造ImageResponse对象
     * @param jsonStr json格式的图像处理结果字符串。
     * @return 图像处理结果对象
     * @throws RequestException
     * @throws InternalException
     */
    public static ImageResponse parse(String jsonStr) throws RequestException, InternalException {
        // 响应的通用处理部分
        Response response =  Response.parse(jsonStr);
        Map<String, Double> results = new HashMap<>();
        // 解析图像信息部分
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        // 发动机部位
        Region region = Region.fromString(jsonObj.getString(ResponseKey.REGION_KEY).toUpperCase());
        // 污染程度的置信度
        JSONArray arrayResults = jsonObj.getJSONArray(ResponseKey.IMAGE_RESULTS_KEY);
        Iterator<Object> iter = arrayResults.iterator();
        while(iter.hasNext()) {
            JSONObject obj = (JSONObject) iter.next();
            results.put(obj.getString(ResponseKey.RESULTS_NAME_KEY),
                    obj.getDouble(ResponseKey.RESULTS_SCORE_KEY));
        }
        return new ImageResponse(response, region, results);
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setRegion(String regionStr){
        this.region = Region.fromString(regionStr.toUpperCase());
    }

    public void setResponse(Response response) {
        this.response = response;
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

    public Region getRegion() {
        return this.region;
    }
}
