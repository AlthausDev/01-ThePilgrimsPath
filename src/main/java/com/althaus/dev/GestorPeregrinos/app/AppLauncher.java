package com.althaus.dev.GestorPeregrinos.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AppLauncher implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);

	public static void main(String[] args) {
		SpringApplication.run(AppLauncher.class);
	}

	@Autowired
	public AppLauncher() {
		StartupManager.getInstance();
		ViewInitializer.getInstance();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			logger.info("La aplicación se ha iniciado correctamente.");
		} catch (Exception e) {
			logger.error("Ocurrió un error durante la inicialización.", e);
		}
	}
}

