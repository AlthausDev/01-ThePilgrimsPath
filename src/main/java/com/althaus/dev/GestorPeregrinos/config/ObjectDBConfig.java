package com.althaus.dev.GestorPeregrinos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
public class ObjectDBConfig {


    @Bean(name = "objectdbEntityManager")
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("peregrinos_samuelalthaus.odb")
                .createEntityManager();
    }

}
