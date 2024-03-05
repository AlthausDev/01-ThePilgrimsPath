package com.althaus.dev.GestorPeregrinos.config;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("db4o")
public class DB4OConfig {

    @Value("${db4o.datasource.filepath}")
    private String db4oFilePath;

    @Bean
    public ObjectContainer db4oObjectContainer() {
        ObjectContainer objectContainer = null;
        try {
            objectContainer = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oFilePath);
        } catch (Exception e) {
            System.err.println("Error al abrir la conexión con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return objectContainer;
    }
}
