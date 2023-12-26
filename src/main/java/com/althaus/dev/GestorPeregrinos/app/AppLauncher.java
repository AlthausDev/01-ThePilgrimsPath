package com.althaus.dev.GestorPeregrinos.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppLauncher {

	public static void main(String[] args) {
		StartupManager startupManager = StartupManager.getInstance();
	}
}

