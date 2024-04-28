package com.althaus.dev.GestorPeregrinos.config;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ExistDBConfig {

    @Value("${spring.datasource.existdb.url}")
    private String existdbUrl;

    @Value("${spring.datasource.existdb.username}")
    private String existdbUsername;

    @Value("${spring.datasource.existdb.password}")
    private String existdbPassword;

    @Value("${spring.datasource.driver-class-name.existdb}")
    private String existdbDriverClassName;

    @Bean
    public String existdbDriverClassNameBean() {
        return existdbDriverClassName;
    }

    @Bean(name = "existdbCollection")
    public Collection existdbCollection() throws XMLDBException {
        return DatabaseManager.getCollection(existdbUrl, existdbUsername, existdbPassword);
    }

    @Bean(name = "existdbXPathQueryService")
    public XPathQueryService existdbXPathQueryService() throws XMLDBException {
        return (XPathQueryService) existdbCollection().getService(XPathQueryService.class);
    }

    @Bean(name = "existdbXQueryService")
    public XQueryService existdbXQueryService() throws XMLDBException {
        return (XQueryService) existdbCollection().getService(XQueryService.class);
    }

    @Bean(name = "collectionManagementService")
    public CollectionManagementService collectionManagementServiceBean() throws XMLDBException {
        return existdbCollection().getService(CollectionManagementService.class);
    }

    public void crearColeccionParaParada(Long idParada) {
        try {
            CollectionManagementService managementService = collectionManagementServiceBean();
            managementService.createCollection(String.valueOf(idParada));
        } catch (XMLDBException e) {
            throw new RuntimeException("Error al crear la colección para la parada: " + idParada, e);
        }
    }

    public void saveCarnetParada(Long idParada, Carnet carnet) {
        try {
            Collection paradaCollection = existdbCollection().getChildCollection(idParada + "_carnets");
            paradaCollection.storeResource((Resource) carnet);
        } catch (XMLDBException e) {
            throw new RuntimeException("Error al guardar el carné en la colección de la parada: " + idParada, e);
        }
    }



    private Database getExistDBDatabase() throws XMLDBException {
        try {
            Collection collection = DatabaseManager.getCollection(existdbUrl, existdbUsername, existdbPassword);

            Class<?> cl = Class.forName(existdbDriverClassName);
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            return database;

        } catch (ClassNotFoundException e) {
            throw new XMLDBException(100, "No se encontró la clase del driver ExistDB", e);
        } catch (InstantiationException e) {
            throw new XMLDBException(101, "No se pudo instanciar la clase del driver ExistDB", e);
        } catch (IllegalAccessException e) {
            throw new XMLDBException(102, "No se pudo acceder al constructor del driver ExistDB", e);
        } catch (NoSuchMethodException e) {
            throw new XMLDBException(103, "No se encontró el constructor del driver ExistDB", e);
        } catch (InvocationTargetException e) {
            throw new XMLDBException(104, "Error al invocar el constructor del driver ExistDB", e);
        }
    }

    public List<Carnet> getCarnetsExpedidosEnParada(Parada parada) {
        List<Carnet> carnets = new ArrayList<>();
        try {
            String paradaCollectionName = String.valueOf(parada.getId());
            Collection paradaCollection = existdbCollection().getChildCollection(paradaCollectionName + "_carnets");
            String[] resources = paradaCollection.listResources().toArray(new String[0]);
            for (String resourceId : resources) {
                Resource resource = paradaCollection.getResource(resourceId);
                carnets.add((Carnet) resource.getContent());
            }
        } catch (XMLDBException e) {
            throw new RuntimeException("Error al obtener los carnets expedidos en la parada: " + parada.getId(), e);
        }
        return carnets;
    }

}
