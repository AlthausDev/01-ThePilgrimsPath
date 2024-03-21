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

//    private static final Logger LOG = LoggerFactory.getLogger(ObjectDBConfig.class);
//
//    @Value("${spring.datasource.url.objectdb}")
//    private String url;
//
//    @Value("${spring.datasource.username.objectdb}")
//    private String username;
//
//    @Value("${spring.datasource.password.objectdb}")
//    private String password;
//
//    @Value("${spring.datasource.driver-class-name.objectdb}")
//    private String driverClassName;
//
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    private String hibernateDialect;
//
//    @Bean("objectdbDataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("peregrinos_samuelalthaus.odb");
    public static EntityManager em = emf.createEntityManager();

//    @Bean(name = "objectdbEntityManagerFactory")
//    @Qualifier("objectdb")
//    public EntityManagerFactory entityManagerFactory() {
//        try {
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource());
//            em.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
//            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//            // Configurar propiedades de Hibernate, incluido el dialecto
//            Properties properties = new Properties();
//            properties.setProperty("hibernate.dialect", hibernateDialect);
//            em.setJpaProperties(properties);
//
//            em.afterPropertiesSet();
//            return (EntityManagerFactory) em.getObject();
//        } catch (Exception e) {
//            LOG.error("Error al crear el EntityManagerFactory para ObjectDB: {}", e.getMessage());
//            throw new RuntimeException("Error al crear el EntityManagerFactory para ObjectDB", e);
//        }
//    }


}
