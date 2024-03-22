package com.althaus.dev.GestorPeregrinos;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.controller.*;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase principal que inicia la aplicación y realiza operaciones al inicio.
 *
 * <p>
 * Esta clase configura y lanza la aplicación Spring Boot. También inicializa
 * la sesión de usuario después de que el contexto de la aplicación se haya
 * actualizado.
 * </p>
 *
 * @author Althaus_Dev
 */

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class AppLauncher implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private StartupManager startupManager;

    @Autowired
    private LoginController loginController;

    @Autowired
    private ParadaController paradaController;

    @Autowired
    private PeregrinoController peregrinoController;

    @Autowired
    private EstanciaController estanciaController;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ParadaRepository paradaRepository;

    @Autowired
    private AdminParadaService adminParadaService;

    @Autowired
    private EnvioACasaService envioACasaService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ServicioController servicioController;


    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(AppLauncher.class, args);
    }

    /**
     * Método que se ejecuta al iniciar la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    @Override
    public void run(String... args) {
        try {
            startupManager.cargarAdminGeneral();
            startupManager.cargarDatosIniciales();
            inicializarSesionDeUsuario();
        } catch (Exception e) {
            log.error("Ocurrió un error durante la inicialización.", e);
        }
    }

    /**
     * Inicializa la sesión de usuario con instancias de controladores y servicios necesarios.
     */
    private void inicializarSesionDeUsuario() {

        UserSession.Session(loginController,
                paradaController,
                peregrinoController,
                estanciaController,
                validationService,
                paradaRepository,
                adminParadaService,
                envioACasaService,
                direccionService,
                servicioService,
                servicioController);
    }
}