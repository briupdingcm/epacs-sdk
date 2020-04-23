package com.epacs.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * 操作String的工具类
 *
 * @author Kevin
 * @version 1.1
 */
public class StringUtils{
    private static StringUtils su;

    private StringUtils(){}

    /**
     * 通过单例模式获取对象
     *
     * @return 单例对象
     */
    public static StringUtils getInstance(){
        if(StringUtils.su==null){
            synchronized(StringUtils.class){
                if(StringUtils.su==null) StringUtils.su=new StringUtils();
            }
        }
        return StringUtils.su;
    }

    /**
     * 重写的toString方法，生成的格式为JSON格式的字符串
     *
     * @param o 要生成JSON格式字符串的对象
     * @return JSON格式的字符串
     */
    public String toString(Object o){
        SerializeConfig config=new SerializeConfig();
        config.propertyNamingStrategy=PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(o,config);
    }
}
