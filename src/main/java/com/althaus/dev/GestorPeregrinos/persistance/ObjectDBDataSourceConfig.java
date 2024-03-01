package com.althaus.dev.GestorPeregrinos.persistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class ObjectDBDataSourceConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("objectdb");
        em.setPersistenceProviderClass(org.objectdb.jpa.Provider.class);
        em.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
        em.afterPropertiesSet();
        return em.getObject();
    }
}
