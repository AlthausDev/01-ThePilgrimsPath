package com.althaus.dev.GestorPeregrinos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
@Profile("objectdb")
public class ObjectDBConfig {

    @Value("${objectdb.datasource.url}")
    private String objectdbUrl;

    @Bean(name = "objectdbEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory(objectdbUrl);
        } catch (Exception e) {
            System.err.println("Error al crear la fábrica de entidades de ObjectDB: " + e.getMessage());
            throw e;
        }
    }
}
