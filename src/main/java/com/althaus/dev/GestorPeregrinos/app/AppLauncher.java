package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.config.ViewInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase principal que inicia la aplicación y realiza operaciones al inicio.
 *
 * @author Althaus_Dev
 */
@SpringBootApplication
@EnableTransactionManagement
public class AppLauncher implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);

	public static void main(String[] args) {
		SpringApplication.run(AppLauncher.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			iniciarAplicacion();
		} catch (Exception e) {
			logger.error("Ocurrió un error durante la inicialización.", e);
		}
	}

	private void iniciarAplicacion() {
		ViewInitializer.getInstance();
		logger.info("La aplicación se ha iniciado correctamente");
	}
}
