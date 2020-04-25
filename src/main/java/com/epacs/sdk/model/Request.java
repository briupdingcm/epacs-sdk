package com.epacs.sdk.model;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.Position;
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
    private Position position;

    public Request() {
    }

    public Request(String image, Position position) {
        this.image = image;
        this.position = position;
    }

    public Request(String image) {
        this.image = image;
    }

    public String doGet(URI point, String token) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(point);
        get.addHeader("Authorization", "Bearer " + token);
        get.addHeader("Content-type", "application/json");
        get.addHeader("Accept", "*/*");
        HttpResponse s = client.execute(get);

        HttpEntity entity = client.execute(get).getEntity();
        return EntityUtils.toString(entity);
    }

    public String doPost(URI point, String token) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(point);
        post.addHeader("Authorization", "Bearer " + token);
        post.addHeader("Content-type", "application/json");
        post.addHeader("Accept", "*/*");
        JSONObject param = new JSONObject();
        param.put("image",image);
        if(position != null)param.put("position", position.name());
        StringEntity se = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);

    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
