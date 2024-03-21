package com.althaus.dev.GestorPeregrinos.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MySQLConfig {

    @Value("${spring.datasource.url.mysql}")
    private String url;

    @Value("${spring.datasource.username.mysql}")
    private String username;

    @Value("${spring.datasource.password.mysql}")
    private String password;

    @Value("${spring.datasource.driver-class-name.mysql}")
    private String driverClassName;



    @Primary
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        try {

            Properties properties = new Properties();

            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

            em.setDataSource(dataSource);
            em.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            em.setJpaProperties(properties);

            return em;
        } catch (Exception e) {
            System.err.println("Error al crear el EntityManagerFactory para MySQL: " + e.getMessage());
            throw e;
        }
    }
}
