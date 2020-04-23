package com.epacs.sdk.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 操作HTTP请求和响应的工具类
 *
 * @author Kevin
 * @version 1.1
 */
public class HttpUtils {
    /**
     * 模拟浏览器发送POST请求
     *
     * @param link  目标链接地址
     * @param token 用户身份标识
     * @param param 发送请求时携带的参数
     * @return 响应体
     * @throws IOException 网络有可能引起IO异常
     */
    public static String post(URI link, String token, JSONObject param) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(link);
        post.addHeader("Authorization", "Bearer " + token);
        post.addHeader("Content-type", "application/json");
        post.addHeader("Accept", "*/*");
        StringEntity se = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    /**
     * 模拟浏览器发送GET请求
     *
     * @param link  目标链接地址，由于是GET请求，所以参数在链接后面
     * @param token 用户身份标识
     * @return 响应体
     * @throws IOException 网络有可能引起IO异常
     */
    public static String get(String link, String token) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(link);
        get.addHeader("Authorization", "Bearer " + token);
        get.addHeader("Content-type", "application/json");
        get.addHeader("Accept", "*/*");
        HttpEntity entity = client.execute(get).getEntity();
        return EntityUtils.toString(entity);
    }

    public static String get(URI url, String token) throws IOException {
        return get(url.toString(), token);
    }
}
