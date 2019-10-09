package com.importer.controllers;

import com.importer.services.LuisService;
import com.importer.utils.JsonManager;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import javax.json.JsonObject;

@Controller
@PropertySource("classpath:luis.properties")
public class LuisController{

    @Autowired
    private Environment env;
    @Autowired
    private LuisService service;
    @Autowired
    private JsonManager jsonmanager;

    public JsonObject addLabel(JsonObject obj){
        JsonObject response = null;
        try{
            String url = env.getProperty("addlabel");
            response = service.post(url,obj);
        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }

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
}
