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

    public JsonObject send(JsonObject obj,String property){
        String url = env.getProperty(property);
        return send(url,obj);
    }

    public JsonObject send(JsonArray obj,String property){
        String url = env.getProperty(property);
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
