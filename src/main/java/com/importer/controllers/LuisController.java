package com.importer.controllers;

import java.io.IOException;

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
        JsonObject response = null;
        try{
            String url = env.getProperty("createentity");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public JsonObject createIntent(JsonObject obj){
        JsonObject response = null;
        try{
            String url = env.getProperty("createintent");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public JsonObject batchAddLabels(JsonValue obj){
        JsonObject response = null;
        try{
            String url = env.getProperty("batchaddlabels");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public JsonObject createHierarchicalEntity(JsonObject obj){
        JsonObject response = null;
        try{
            String url = env.getProperty("createhierarchicalentity");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public JsonObject createClosedListEntity(JsonObject obj){
        JsonObject response = null;
        try{
            String url = env.getProperty("createclosedlistentity");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
