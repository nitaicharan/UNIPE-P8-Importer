package com.importer.controllers;

import javax.json.Json;
import javax.json.JsonObject;

import com.importer.controllers.LuisController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
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
    public void createEntityTest(){
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Deputadx")
            .build();
        JsonObject obj = controller.createEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    @Test
    public void createIntentTest(){
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Somar")
            .build();
        JsonObject obj = controller.createIntent(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    @Test
    public void createHierarchicalEntityTest(){
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Data")
            .add("children", Json.createArrayBuilder()
                    .add("Inicio")
                    .add("Fim"))
            .build();
        JsonObject obj = controller.createHierarchicalEntity(json);
        Assert.assertTrue(obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }

    @Test
    @AfterAll
    public void batchAddLabelsTest(){
        String deputadx = "Kim Kataguiri";
        String data = "2019";
        String intentName = "Somar";

        String entityName1 = "Deputadx";
        String entityName2 = "Data";

        String text1 = "Quanto "+deputadx+" gastou no ano de "+data+"?";

        int startCharIndex1 = text1.indexOf(deputadx);
        int endCharIndex1 = text1.indexOf(deputadx)+deputadx.length()-1;

        int startCharIndex2 = text1.indexOf(data);
        int endCharIndex2 = text1.indexOf(data)+data.length()-1;

        JsonObject json = Json.createObjectBuilder()
            .add("text", text1)
            .add("intentName",intentName)
            .add("entityLabels", Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("entityName", entityName1)
                        .add("startCharIndex",startCharIndex1)
                        .add("endCharIndex", endCharIndex1))
                    .add(Json.createObjectBuilder()
                        .add("entityName", entityName2)
                        .add("startCharIndex",startCharIndex2)
                        .add("endCharIndex", endCharIndex2)))
            .build();

        JsonObject obj = controller.batchAddLabels(json);
        Assert.assertTrue(obj.getString("response"),obj.getInt("code") == 200 || obj.getInt("code") == 201);
    }
}
