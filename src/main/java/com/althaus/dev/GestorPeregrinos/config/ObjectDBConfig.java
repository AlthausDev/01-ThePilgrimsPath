package com.althaus.dev.GestorPeregrinos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@Configuration
public class ObjectDBConfig {


    @Bean(name = "objectdbEntityManager")
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("peregrinos_samuelalthaus.odb")
                .createEntityManager();
    }

}
