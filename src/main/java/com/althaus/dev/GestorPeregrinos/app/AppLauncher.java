package com.althaus.dev.GestorPeregrinos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
//@ComponentScan("com.althaus.dev.GestorPeregrinos")
//@EntityScan("com.althaus.dev.GestorPeregrinos.model")
//@EnableJpaRepositories(basePackages = "com.althaus.dev.GestorPeregrinos.repository")
public class AppLauncher {

	@Bean
	public AppLauncher applicationStartupRunner() {
		return new AppLauncher();
	}

	public static void main(String[] args) {
		//StartupManager startupManager = StartupManager.getInstance();
		SpringApplication.run(com.althaus.dev.GestorPeregrinos.app.AppLauncher.class);
	}
}

