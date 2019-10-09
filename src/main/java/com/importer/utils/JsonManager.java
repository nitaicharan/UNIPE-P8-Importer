package com.importer.utils;

import javax.json.JsonObject;

import org.springframework.stereotype.Component;

import javax.json.Json;

import java.io.File;
import java.io.FileReader;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public final class JsonManager {
    public static Set<JsonObject> toSetJsonObjects(String rootpath){
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

    public static JsonObject toJsonObject(InputStream stream, int code) throws IOException{
        InputStreamReader streamreader = new InputStreamReader(stream);
        StringBuilder sb = new StringBuilder();

        String line = null;

        BufferedReader bufferedReader = new BufferedReader(streamreader);
        while ((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }

        return Json.createObjectBuilder()
            .add("code",code)
            .add("response",sb.toString().replaceAll("\r\n","").replaceAll("\n",""))
            .build();
    }
}
