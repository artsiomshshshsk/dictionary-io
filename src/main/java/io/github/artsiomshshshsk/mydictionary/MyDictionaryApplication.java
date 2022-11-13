package io.github.artsiomshshshsk.mydictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MyDictionaryApplication{

    public static void main(String[] args) {
        SpringApplication.run(MyDictionaryApplication.class, args);
    }
}
