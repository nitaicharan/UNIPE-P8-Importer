package com.importer.controllers;

import javax.json.JsonObject;
import javax.json.Json;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class JsonManager {
    public Set<JsonObject> toSetJsonObjects(String rootpath){

        Set<JsonObject> conteudos = new HashSet<JsonObject>();
        File file = new File(rootpath);
        FileReader reader = null;

        try {
            List<String> paths = Arrays.asList(file.list());

            for(String path:paths){
                path = rootpath+path;

                reader = new FileReader(path);

                conteudos.add(Json.createReader(reader).readObject());
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return conteudos;
    }

    public String toString(InputStream stream){
        InputStreamReader streamreader = new InputStreamReader(stream);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try (BufferedReader bufferedReader = new BufferedReader(streamreader)) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        } 
        return sb.toString().replaceAll("\r\n","").replaceAll("\n","");
    }

    public JsonObject toJsonObject(InputStream stream){
        return Json.createReader(stream).readObject();
    }
/*
    public JsonObject toJsonObject(InputStream stream){

        InputStreamReader streamreader = new InputStreamReader(stream);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try (BufferedReader bufferedReader = new BufferedReader(streamreader)) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        } 
        String json = sb.toString().replaceAll("\r\n","").replaceAll("\n","");
        return Json.createReader(new StringReader(json)).readObject();
    }
*/

    public JsonManager(){
    }
}
