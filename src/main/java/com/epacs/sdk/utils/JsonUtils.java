package com.epacs.sdk.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: jieyi-product-0
 * @package: com.briup.jieyi.product.api.utils
 * @filename: JsonUtils.java
 * @create: 2020.04.13 19:21
 * @author: Kevin
 * @description: .
 **/
public class JsonUtils{
    public static boolean isJsonData(String str){
        try{
            JSONObject.parseObject(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static String getStringValue(String jsonString,String key){
        if(JsonUtils.isJsonData(jsonString)){
            String value=JSONObject.parseObject(jsonString).getString(key);
            return value==null?"":value;
        }else{
            return "";
        }
    }

    public static int getIntValue(String jsonString,String key){
        if(JsonUtils.isJsonData(jsonString)){
            String value=JSONObject.parseObject(jsonString).getString(key);
            return value==null?-1:Integer.parseInt(value);
        }else{
            return -1;
        }
    }

    public static long getLongValue(String jsonString,String key){
        if(JsonUtils.isJsonData(jsonString)){
            String value=JSONObject.parseObject(jsonString).getString(key);
            return value==null?-1:Long.parseLong(value);
        }else{
            return -1;
        }
    }

    public static double getDoubleValue(String jsonString,String key){
        if(JsonUtils.isJsonData(jsonString)){
            String value=JSONObject.parseObject(jsonString).getString(key);
            return value==null?-1:Double.parseDouble(value);
        }else{
            return -1.0D;
        }
    }
}
