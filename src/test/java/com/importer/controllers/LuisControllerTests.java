package com.importer.controllers;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.importer.utils.JsonManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuisControllerTests {

    @Autowired
    private LuisController controller;

    @Test
    public void contextLoads(){
    }

    public void deputadxCreateEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/entitydeputadx.json");
        JsonObject obj = controller.send(json, "createentity");
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void dataCreateHierarchicalEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/hierarchicalentitydata.json");
        JsonObject obj = controller.send(json,"createhierarchicalentity");
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void deputadxCreateClosedListEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/closedlistentitydeputadx.json");
        JsonObject obj = controller.send(json,"createclosedlistentity");
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void somarBatchAddPatternsTest(){
        JsonArray json = JsonManager.toJsonArray("tmp/batchaddpatternssomar.json");
        JsonObject obj = controller.send(json,"batchaddpatterns");
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void addLabelTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/addlabel.json");
        JsonObject obj = controller.send(json,"addlabel");
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void somarCreateIntentTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/intentsomar.json");
        JsonObject obj = controller.send(json,"createintent");
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void mostrarCreateIntentTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/intentmostrar.json");
        JsonObject obj = controller.send(json,"createintent");
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void somarBatchAddLabelsTest(){
        JsonArray json = JsonManager.toJsonArray("tmp/batchaddlabelssomar.json");
        JsonObject obj = controller.send(json,"batchaddlabels");
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void mostrarBatchAddLabelsTest(){
        JsonArray json = JsonManager.toJsonArray("tmp/batchaddlabelsmostrar.json");
        JsonObject obj = controller.send(json,"batchaddlabels");
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }
}
