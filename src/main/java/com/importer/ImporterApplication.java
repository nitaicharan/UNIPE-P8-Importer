package com.importer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImporterApplication implements CommandLineRunner {

    public static void main(String[] args){
        SpringApplication.run(ImporterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
