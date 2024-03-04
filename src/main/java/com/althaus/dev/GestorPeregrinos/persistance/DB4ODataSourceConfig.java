package com.althaus.dev.GestorPeregrinos.persistance;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("db4o")
public class DB4ODataSourceConfig {

    @Bean
    public ObjectContainer objectContainer() {
        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "pilgrim.db4o");
    }
}
