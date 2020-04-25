package com.epacs.sdk.recognition;

import java.util.Map;

/**
 * 回调接口
 *
 * @author: Kevin
 * @version 1.1
 **/
public interface ResultCallback{
    /**
     *异步查询图像的识别结果，如果识别完成，则以JSON格式的数据返回
     *
     * @param logId 唯一的id，用于问题定位
     * @param errorCode 错误码
     * @param errorMsg 错误消息
     * @param results 识别结果
     */
    void callback(int logId, int errorCode, String errorMsg, Map<String, Double> results);
}
