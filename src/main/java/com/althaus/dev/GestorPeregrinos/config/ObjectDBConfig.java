package com.althaus.dev.GestorPeregrinos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ObjectDBConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectDBConfig.class);

    @Value("${spring.datasource.url.objectdb}")
    private String url;

    @Value("${spring.datasource.username.objectdb}")
    private String username;

    @Value("${spring.datasource.password.objectdb}")
    private String password;

    @Value("${spring.datasource.driver-class-name.objectdb}")
    private String driverClassName;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Bean("objectdbDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "objectdbEntityManager")
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("peregrinos_samuelalthaus.odb")
                .createEntityManager();
    }

}
