package com.althaus.dev.GestorPeregrinos.config;

import com.db4o.ObjectContainer;
import com.db4o.Db4oEmbedded;
import com.db4o.config.EmbeddedConfiguration;
//import jakarta.activation.DataSource;
//import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class DB4OConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DB4OConfig.class);

    @Value("${spring.datasource.url.db4o}")
    private String db4oFilePath;

    private ObjectContainer db4oInstance;

    @Bean
    @Qualifier("db4o")
    public ObjectContainer db4oObjectContainer() {
        if (db4oInstance == null) {
            ensureDirectoryExists(db4oFilePath);
            db4oInstance = openDb4o(db4oFilePath);
        }
        return db4oInstance;
    }

    @Bean(name = "db4oEntityManagerFactory")
    @Qualifier("db4o")
    public EntityManagerFactory db4oEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.althaus.dev.GestorPeregrinos.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return (EntityManagerFactory) emf.getObject();
    }

    private void ensureDirectoryExists(String filePath) {
        Path path = Paths.get(filePath).getParent();
        if (path != null && !Files.exists(path)) {
            try {
                Files.createDirectories(path);
                LOG.info("Directorio para la base de datos DB4O creado en: {}", path);
            } catch (Exception e) {
                LOG.error("No se pudo crear el directorio para la base de datos DB4O", e);
                throw new RuntimeException("No se pudo crear el directorio para la base de datos DB4O", e);
            }
        }
    }

    private ObjectContainer openDb4o(String db4oFilePath) {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        LOG.info("Intentando abrir o crear la base de datos DB4O en la ruta: {}", db4oFilePath);
        try {
            return Db4oEmbedded.openFile(config, db4oFilePath);
        } catch (Exception e) {
            LOG.error("No se pudo abrir ni crear la base de datos DB4O en la ruta: {}", db4oFilePath, e);
            throw new RuntimeException("No se puede abrir ni crear la conexión con la base de datos DB4O", e);
        }
    }
}

