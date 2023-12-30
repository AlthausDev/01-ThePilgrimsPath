package com.althaus.dev.GestorPeregrinos.config;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.util.io.XMLReader;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Optional;

/**
 * Gestor de inicio de la aplicación que realiza operaciones de inicialización.
 *
 * <p>Este gestor es responsable de realizar tareas específicas durante el inicio de la aplicación.</p>
 *
 * @author Althaus_Dev
 */
@Slf4j
@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.althaus.dev.GestorPeregrinos")
@EnableJpaRepositories("com.althaus.dev.GestorPeregrinos.repository")
@EntityScan(basePackages = "com.althaus.dev.GestorPeregrinos.model")
@EnableAutoConfiguration
public class StartupManager {

    private static final Logger logger = LoggerFactory.getLogger(StartupManager.class);
    private static final StartupManager INSTANCE = new StartupManager();

    private HashMap<String, String> nacionalidades;
    private Optional<Parada> paradaActual;

    @Autowired
    private ParadaRepository paradaRepository;

    /**
     * Realiza operaciones específicas durante el inicio de la aplicación.
     */
    @PostConstruct
    private void init() {
        try {
            nacionalidades = XMLReader.readPaises();
        } catch (Exception e) {
            logger.error("Error al cargar datos desde la base de datos.", e);
        }

        try {
            long numParadas = paradaRepository.count();
            logger.info("Número de paradas en la base de datos: {}", numParadas);
            if (numParadas > 0) {
                paradaActual = getParadaRandom(numParadas);
                logger.info("Parada actual asignada: {}", paradaActual);
            } else {
                logger.warn("No hay paradas disponibles en la base de datos.");
            }
        } catch (Exception e) {
            logger.error("Error al obtener y asignar la parada aleatoria.", e);
        }
    }

    /**
     * Obtiene una parada aleatoria en base al número total de paradas.
     *
     * @param numParadas Número total de paradas.
     * @return Una parada aleatoria.
     */
    private Optional<Parada> getParadaRandom(long numParadas) {
        long paradaAleatoria = (long) (Math.random() * numParadas);
        return paradaRepository.findById(paradaAleatoria);
    }

    public static StartupManager getInstance(){
        return INSTANCE;
    }
}

