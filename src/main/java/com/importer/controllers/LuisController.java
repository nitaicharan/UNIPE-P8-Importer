package com.importer.controllers;

import java.io.IOException;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import com.importer.services.LuisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
@PropertySource("classpath:luis.properties")
public class LuisController{

    @Autowired
    private Environment env;
    @Autowired
    private LuisService service;

    public JsonObject createEntity(JsonObject obj){
        String url = env.getProperty("createentity");
        return send(url,obj);
    }

    public JsonObject createIntent(JsonObject obj){
        String url = env.getProperty("createintent");
        return send(url,obj);
    }

    public JsonObject batchAddLabels(JsonArray obj){
        String url = env.getProperty("batchaddlabels");
        return send(url,obj);
    }

    public JsonObject batchAddPatterns(JsonArray obj){
        String url = env.getProperty("batchaddpatterns");
        return send(url,obj);
    }

    public JsonObject createHierarchicalEntity(JsonObject obj){
        String url = env.getProperty("createhierarchicalentity");
        return send(url,obj);
    }

    public JsonObject createClosedListEntity(JsonObject obj){
        String url = env.getProperty("createclosedlistentity");
        return send(url,obj);
    }

    private JsonObject send(String url,JsonValue obj){
        try{
            return service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
