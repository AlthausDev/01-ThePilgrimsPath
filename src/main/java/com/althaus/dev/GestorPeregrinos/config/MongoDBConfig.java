package com.althaus.dev.GestorPeregrinos.config;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessResourceFailureException;

@Configuration
@Profile("mongodb")
public class MongoDBConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() {
        try {
            return MongoClients.create("mongodb://" + host + ":" + port);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("Error al crear el cliente de MongoDB", e);
        }
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        try {
            return mongoClient().getDatabase(databaseName);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("Error al obtener la base de datos MongoDB", e);
        }
    }
}
