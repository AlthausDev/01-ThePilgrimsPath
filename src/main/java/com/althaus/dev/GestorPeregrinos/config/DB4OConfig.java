package com.althaus.dev.GestorPeregrinos.config;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase de configuración para la base de datos DB4O.
 */
@Configuration
public class DB4OConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DB4OConfig.class);

    @Value("${db4o.datasource.filepath}")
    private String db4oFilePath;

    private ObjectContainer db4oInstance;

    /**
     * Crea e inicializa una instancia del contenedor de objetos DB4O.
     *
     * @return el contenedor de objetos DB4O
     */
    @Bean
    @Qualifier("db4o")
    public ObjectContainer db4oObjectContainer() {
        if (db4oInstance == null) {
            ensureDirectoryExists(db4oFilePath);
            db4oInstance = openDb4o(db4oFilePath);
        }
        return db4oInstance;
    }

    /**
     * Asegura que el directorio para la base de datos DB4O exista.
     *
     * @param filePath el camino del archivo de la base de datos DB4O
     */
    private void ensureDirectoryExists(String filePath) {
        Path path = Paths.get(filePath).getParent();
        if (path != null && !Files.exists(path)) {
            try {
                Files.createDirectories(path);
                LOG.info("Directorio para la base de datos DB4O creado en: {}", path);
            } catch (IOException e) {
                LOG.error("No se pudo crear el directorio para la base de datos DB4O", e);
                throw new RuntimeException("No se pudo crear el directorio para la base de datos DB4O", e);
            }
        }
    }

    /**
     * Abre o crea la base de datos DB4O en la ruta especificada.
     *
     * @param db4oFilePath el camino del archivo a la base de datos DB4O
     * @return el contenedor de la base de datos DB4O
     */
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
