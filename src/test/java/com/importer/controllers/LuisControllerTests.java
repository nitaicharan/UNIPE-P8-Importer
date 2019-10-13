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

    public void contextLoads(){
    }

    public void createEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/entity.json");
        JsonObject obj = controller.createEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void createIntentTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/intent.json");
        JsonObject obj = controller.createIntent(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void createHierarchicalEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/hierarchicalentity.json");
        JsonObject obj = controller.createHierarchicalEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void createClosedListEntityTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/closedlistentity.json");
        JsonObject obj = controller.createClosedListEntity(json);
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void addLabelTest(){
        JsonObject json = JsonManager.toJsonObject("tmp/addlabel.json");
        JsonObject obj = controller.addLabel(json);
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    public void batchAddLabelsTest(){
        JsonArray json = JsonManager.toJsonArray("tmp/batchaddlabels.json");
        JsonObject obj = controller.batchAddLabels(json);
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    @Test
    public void batchAddPatternsTest(){
        JsonArray json = JsonManager.toJsonArray("tmp/batchaddpatterns.json");
        JsonObject obj = controller.batchAddPatterns(json);
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }
}
