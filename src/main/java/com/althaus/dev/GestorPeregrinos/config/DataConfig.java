//package com.althaus.dev.GestorPeregrinos.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//public class DataConfig {
//
//    @Value("${spring.datasource.url.mysql}")
//    private String mysqlUrl;
//
//    @Value("${spring.datasource.username.mysql}")
//    private String mysqlUsername;
//
//    @Value("${spring.datasource.password.mysql}")
//    private String mysqlPassword;
//
//    @Value("${spring.datasource.driver-class-name.mysql}")
//    private String mysqlDriverClassName;
//
//    @Value("${spring.datasource.url.db4o}")
//    private String db4oUrl;
//
//    @Value("${spring.datasource.username.db4o}")
//    private String db4oUsername;
//
//    @Value("${spring.datasource.password.db4o}")
//    private String db4oPassword;
//
//    @Value("${spring.datasource.driver-class-name.db4o}")
//    private String db4oDriverClassName;
//
//    @Value("${spring.datasource.url.objectdb}")
//    private String objectdbUrl;
//
//    @Value("${spring.datasource.username.objectdb}")
//    private String objectdbUsername;
//
//    @Value("${spring.datasource.password.objectdb}")
//    private String objectdbPassword;
//
//    @Value("${spring.datasource.driver-class-name.objectdb}")
//    private String objectdbDriverClassName;
//
//    @Bean(name = "entityManagerFactory")
//    @Qualifier("mysql")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(@Qualifier("dataSourceMySQL") DataSource dataSource) {
//        return createEntityManagerFactory(dataSource, mysqlDriverClassName, mysqlUrl);
//    }
//
////    @Bean(name = "db4oEntityManagerFactory")
////    @Qualifier("db4o")
////    public LocalContainerEntityManagerFactoryBean db4oEntityManagerFactory(@Qualifier("dataSourceDb4o")DataSource dataSource) {
////        return createEntityManagerFactory(dataSource, db4oDriverClassName, db4oUrl);
////    }
//
//    @Bean(name = "objectdbEntityManagerFactory")
//    @Qualifier("objectdb")
//    public LocalContainerEntityManagerFactoryBean objectdbEntityManagerFactory(@Qualifier("dataSourceObjectDB")DataSource dataSource) {
//        return createEntityManagerFactory(dataSource, objectdbDriverClassName, objectdbUrl);
//    }
//
//    private LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource, String driverClassName, String url) {
//        try {
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource);
//            em.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
//            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//            // Configurar el proveedor JPA
//            Properties jpaProperties = new Properties();
//            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Cambia el dialecto según la base de datos
//            jpaProperties.put("hibernate.show_sql", "true"); // Opcional: mostrar SQL en consola
//            em.setJpaProperties(jpaProperties);
//
//            return em;
//        } catch (Exception e) {
//            System.err.println("Error al crear el EntityManagerFactory: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Bean(name = "dataSourceMySQL")
//    public DataSource dataSourceMySQL() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(mysqlDriverClassName);
//        dataSource.setUrl(mysqlUrl);
//        dataSource.setUsername(mysqlUsername);
//        dataSource.setPassword(mysqlPassword);
//        return dataSource;
//    }
//
////    @Bean(name = "dataSourceDb4o")
////    public DataSource dataSourceDb4o() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName(db4oDriverClassName);
////        dataSource.setUrl(db4oUrl);
////        dataSource.setUsername(db4oUsername);
////        dataSource.setPassword(db4oPassword);
////        return dataSource;
////    }
//
//    @Bean(name = "dataSourceObjectDB")
//    public DataSource dataSourceObjectDB() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(objectdbDriverClassName);
//        dataSource.setUrl(objectdbUrl);
//        dataSource.setUsername(objectdbUsername);
//        dataSource.setPassword(objectdbPassword);
//        return dataSource;
//    }
//}
