package com.importer.controllers;

import com.importer.controllers.JsonManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.core.env.Environment;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.json.JsonObject;

@Controller
@PropertySource("classpath:luis.properties")
public class LuisManager{

    @Autowired
    private Environment env;

    @Value("${hostname}")
    private String hostname;
    @Value("${appid}")
    private String appid;
    @Value("${version}")
    private String version;
    @Value("${subscriptionkey}")
    private String subscriptionkey;
    @Value("${port}")
    private String port;

    public String createEntity(JsonObject json){
        String url = env.getProperty("entities");
        url = String.format(url,hostname,appid,version);
        return send(url,json);
    }

    public String addLabel(JsonObject json){
        String url = env.getProperty("exemple");
        url = String.format(url,hostname,appid,version);
        return send(url,json);
    }

    public String send(String url,JsonObject json){
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            request.addHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key",subscriptionkey);
            request.setEntity(new StringEntity(json.toString()));

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return  new JsonManager().toString(entity.getContent());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
