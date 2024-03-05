package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.EstanciaController;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.controller.ParadaController;
import com.althaus.dev.GestorPeregrinos.controller.PeregrinoController;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
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
//@Profile({"objectdb", "db4o", "mysql"})
public class AppLauncher implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private StartupManager startupManager;



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
        // Obtener instancias de controladores desde el contexto de la aplicación
        LoginController loginController = applicationContext.getBean(LoginController.class);
        ParadaController paradaController = applicationContext.getBean(ParadaController.class);
        PeregrinoController peregrinoController = applicationContext.getBean(PeregrinoController.class);
        EstanciaController estanciaController = applicationContext.getBean(EstanciaController.class);
        ValidationService validationService = applicationContext.getBean(ValidationService.class);
        ParadaRepository paradaRepository = applicationContext.getBean(ParadaRepository.class);
        AdminParadaService adminParadaService = applicationContext.getBean(AdminParadaService.class);
        EnvioACasaService envioACasaService = applicationContext.getBean(EnvioACasaService.class);
        DireccionService direccionService = applicationContext.getBean(DireccionService.class);
        ServicioService servicioService = applicationContext.getBean(ServicioService.class);

        // Inyectar dependencias en UserSession
        UserSession.Session(loginController,
                paradaController,
                peregrinoController,
                estanciaController,
                validationService,
                paradaRepository,
                adminParadaService,
                envioACasaService,
                direccionService,
                servicioService);
    }
}