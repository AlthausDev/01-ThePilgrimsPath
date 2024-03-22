//package com.althaus.dev.GestorPeregrinos.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class MySQLConfig {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//
//
//    @Primary
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        try {
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource);
//            em.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
//            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//            Properties properties = new Properties();
//            em.setJpaProperties(properties);
//
//            return em;
//        } catch (Exception e) {
//            throw new RuntimeException("Error al crear el EntityManagerFactory para MySQL", e);
//        }
//    }
//}
