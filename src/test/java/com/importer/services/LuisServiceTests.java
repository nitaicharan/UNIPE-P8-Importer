package com.importer.services;

import javax.json.Json;
import javax.json.JsonObject;

import com.importer.controllers.LuisController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuisServiceTests {

    @Autowired
    private LuisController controller;

    @Test
    public void createEntityTestDeputadx(){
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Deputadx")
            .build();
        JsonObject obj = controller.createEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    @Test
    public void createHierarchicalEntityDataInicioFim(){
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Data")
            .add("children", Json.createArrayBuilder()
                    .add("Inicio")
                    .add("Fim"))
            .build();
        JsonObject obj = controller.createHierarchicalEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }
}
