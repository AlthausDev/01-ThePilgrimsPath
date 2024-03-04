package com.althaus.dev.GestorPeregrinos.persistance;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Profile("objectdb")
public class ObjectDBDataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.althaus.dev.GestorPeregrinos.model")
                .persistenceUnit("objectdb")
                .build();
    }
}
