package com.althaus.dev.GestorPeregrinos.app;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Gestor de inicio de la aplicación que realiza operaciones de inicialización.
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.althaus.dev.GestorPeregrinos")
@EnableJpaRepositories("com.althaus.dev.GestorPeregrinos.repository")
@EntityScan(basePackages = "com.althaus.dev.GestorPeregrinos.model")
@EnableAutoConfiguration
public class StartupManager {


    private static final StartupManager INSTANCE = new StartupManager();


    /**
     * Constructor que realiza la inicialización de la instancia.
     */
    @Autowired
    public StartupManager() {
        init();
    }

    /**
     * Método de inicialización que realiza operaciones específicas.
     */
    @PostConstruct
    private void init() {
        try {
            //Pendiente
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático para obtener la instancia única de StartupManager.
     *
     * @return La instancia de StartupManager.
     */
    public static StartupManager getInstance() {
        return INSTANCE;
    }
}
