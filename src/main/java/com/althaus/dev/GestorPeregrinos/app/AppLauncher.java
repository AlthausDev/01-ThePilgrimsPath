package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.EstanciaController;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.controller.ParadaController;
import com.althaus.dev.GestorPeregrinos.controller.PeregrinoController;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase principal que inicia la aplicación y realiza operaciones al inicio.
 *
 * @author Althaus_Dev
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.althaus.dev.GestorPeregrinos"})
@EnableJpaRepositories("com.althaus.dev.GestorPeregrinos.repository")
@EntityScan("com.althaus.dev.GestorPeregrinos.model")
@PropertySource("classpath:application.properties")
public class AppLauncher implements CommandLineRunner {

	@Autowired
	private CredencialesService credencialesService;

	@Autowired
	private ParadaRepository paradaRepository;

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

	private UserSession userSession;

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

	}

	@EventListener(ContextRefreshedEvent.class)
	public void generarSesionTrasCargar() {
		try {
			userSession = new UserSession(loginController, paradaController,
					peregrinoController, estanciaController,
					validationService, paradaRepository);
		} catch (RuntimeException e) {
			log.error("Error durante la inicialización de la sesion.", e);
		} catch (Exception e) {
			log.error("Ocurrió un error durante la inicialización.", e);
		}
	}
}
