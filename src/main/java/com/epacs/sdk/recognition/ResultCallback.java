package com.epacs.sdk.recognition;

import com.epacs.sdk.model.ImageResponse;

import java.util.Map;

/**
 * 回调接口
 *
 * @author: Kevin
 * @version 1.1
 **/
public interface ResultCallback{
    /**
     * 异步获得图像的识别结果
     *
     * @param response 图像处理结果
     */
    public void callback(ImageResponse response);
}
