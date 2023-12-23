package com.althaus.dev.GestorPeregrinos.app;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppLauncher {

	private static LoginController loginController;
	public static void main(String[] args) {
		//SpringApplication.run(com.althaus.dev.GestorPeregrinos.app.AppLauncher.class, args);
		loginController = new LoginController();
	}
}

