package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.config.StartupManager;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.althaus.dev.GestorPeregrinos.util.PasswordUtils.hashPassword;

/**
 * Clase principal que inicia la aplicación y realiza operaciones al inicio.
 *
 * @author Althaus_Dev
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.althaus.dev.GestorPeregrinos.config"})
public class AppLauncher implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);

	@Autowired
	private CredencialesService credencialesService;
	public static void main(String[] args) {
		SpringApplication.run(AppLauncher.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			//LoginController.getInstance();
		} catch (Exception e) {
			logger.error("Ocurrió un error durante la inicialización.", e);
		}
	}
}
