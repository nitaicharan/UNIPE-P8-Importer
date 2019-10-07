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

    public String createEntity(JsonObject jentity){
        String url = env.getProperty("createentities");

        url = String.format(url,hostname,appid,version);
        String result = null;

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            request.addHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key",subscriptionkey);
            request.setEntity(new StringEntity(jentity.toString()));

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            result = new JsonManager().toString(entity.getContent());
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
