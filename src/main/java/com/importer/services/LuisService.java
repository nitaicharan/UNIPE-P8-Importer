package com.importer.services;

import java.io.IOException;

import javax.json.JsonObject;
import javax.json.JsonValue;

import com.importer.utils.JsonManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:luis.properties")
public class LuisService{

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

    public JsonObject post(String url,JsonValue json) throws IOException{
        url = String.format(url,hostname,appid,version);

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type","application/json");
        request.setHeader("Ocp-Apim-Subscription-Key",subscriptionkey);
        request.setEntity(new StringEntity(json.toString()));

        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();


        return  JsonManager.toJsonObject(
                entity.getContent()
                ,response.getStatusLine().getStatusCode());
    }

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSubscriptionkey() {
		return subscriptionkey;
	}

	public void setSubscriptionkey(String subscriptionkey) {
		this.subscriptionkey = subscriptionkey;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
