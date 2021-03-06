package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.Region;
import com.epacs.sdk.common.RequestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class Request {
    private String image;
    private Region region;

    public Request() {
    }

    public Request(String image, Region region) {
        this.image = image;
        this.region = region;

    }

    public Request(String image) {
        this(image, null);
    }

    public String doGet(URI point, String token) throws IOException, RequestException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(point);
        get.addHeader("Authorization", "Bearer " + token);
        get.addHeader("Content-type", "application/json");
        get.addHeader("Accept", "*/*");
        HttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != 200 && statusCode != 201)
            throw new RequestException(statusCode, response.getStatusLine().toString());
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    public String doPost(URI point, String token) throws IOException, RequestException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(point);
        post.addHeader("Authorization", "Bearer " + token);
        post.addHeader("Content-type", "application/json");
        post.addHeader("Accept", "*/*");
        JSONObject param = new JSONObject();
        param.put("image",image);
        if(region != null)param.put("position", region.name());
        StringEntity se = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        if((statusCode != 200) && (statusCode != 201))
            throw new RequestException(statusCode, response.getStatusLine().toString());
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);

    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
