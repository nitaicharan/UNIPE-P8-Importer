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

                JsonObject json = Json.createObjectBuilder()
                    .add("name", nome)
                    .build();
                System.out.println(luis.createEntity(json));

                String text;

                text = "Quanto gastou "+nome+" no ano de 2019?";
                System.out.println(luis.addLabel(createJson(text,nome)));

                text = "Quanto roubou "+nome+" entre em 3 meses?";
                System.out.println(luis.addLabel(createJson(text,nome)));
            });
        });
    }

    private JsonObject createJson(String text, String nome){
        return Json.createObjectBuilder()
            .add("text", text)
            .add("intentName","Somar")
            .add("entityLabels", Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                        .add("entityName", "Deputadx")
                        .add("startCharIndex", text.indexOf(nome))
                        .add("endCharIndex", text.indexOf(nome) + nome.length())))
            .build();
    }
}
