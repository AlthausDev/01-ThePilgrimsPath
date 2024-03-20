//package com.althaus.dev.GestorPeregrinos.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.xmldb.api.base.Collection;
//import org.xmldb.api.base.Database;
//import org.xmldb.api.base.XMLDBException;
//import org.xmldb.api.modules.XPathQueryService;
//import org.xmldb.api.modules.XQueryService;
//import org.xmldb.api.DatabaseManager;
//
//@Configuration
//@Profile("existdb")
//public class ExistDBConfig {
//
//    @Value("${spring.datasource.existdb.url}")
//    private String existdbUrl;
//
//    @Value("${spring.datasource.existdb.username}")
//    private String existdbUsername;
//
//    @Value("${spring.datasource.existdb.password}")
//    private String existdbPassword;
//
//    @Bean(name = "existdbDatabase")
//    public Database existdbDatabase() {
//        try {
//            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
//            Database database = (Database) cl.newInstance();
//            database.setProperty("create-database", "true");
//            DatabaseManager.registerDatabase(database);
//            return database;
//        } catch (Exception e) {
//            throw new RuntimeException("Error al configurar ExistDB Database: " + e.getMessage(), e);
//        }
//    }
//
//    @Bean(name = "existdbCollection")
//    public Collection existdbCollection() {
//        try {
//            Database database = existdbDatabase();
//            return DatabaseManager.getCollection(existdbUrl, existdbUsername, existdbPassword);
//        } catch (XMLDBException e) {
//            throw new RuntimeException("Error al obtener colección ExistDB: " + e.getMessage(), e);
//        }
//    }
//
//    @Bean(name = "existdbXPathQueryService")
//    public XPathQueryService existdbXPathQueryService() {
//        try {
//            Collection collection = existdbCollection();
//            return (XPathQueryService) collection.getService(XPathQueryService.class);
//        } catch (XMLDBException e) {
//            throw new RuntimeException("Error al obtener XPathQueryService de ExistDB: " + e.getMessage(), e);
//        }
//    }
//
//    @Bean(name = "existdbXQueryService")
//    public XQueryService existdbXQueryService() {
//        try {
//            Collection collection = existdbCollection();
//            return (XQueryService) collection.getService(XQueryService.class);
//        } catch (XMLDBException e) {
//            throw new RuntimeException("Error al obtener XQueryService de ExistDB: " + e.getMessage(), e);
//        }
//    }
//}
