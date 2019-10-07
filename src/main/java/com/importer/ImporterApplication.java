package com.importer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;

import com.importer.controllers.LuisManager;
import com.importer.controllers.JsonManager;

import javax.json.Json;
import javax.json.JsonObject;

import java.text.Normalizer;

import java.util.Set;

@SpringBootApplication
public class ImporterApplication implements CommandLineRunner {

    private LuisManager luis;
    private JsonManager jsm;

    public ImporterApplication(LuisManager luis, JsonManager jsm){
        this.luis = luis;
        this.jsm = jsm;
    }

    public static void main(String[] args){
        SpringApplication.run(ImporterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Set<JsonObject> conteudos = jsm.toSetJsonObjects("./tmp/");

        conteudos.forEach(conteudo->{
            conteudo.getJsonArray("dados").forEach(dados ->{
               String nome = ((JsonObject) dados).getString("nomeCivil");
               nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
               String response = luis.createEntity(Json.createObjectBuilder().add("name", nome).build());
               System.out.println(response);
            });
        });
    }
}
