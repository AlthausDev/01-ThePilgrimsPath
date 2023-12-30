package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.app.AppLauncher;
import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);
    private final CredencialesService credencialesService;
    private UserSession userSession;

    @Autowired
    public LoginController(CredencialesService credencialesService) {
        this.credencialesService = credencialesService;

        try {
            String path = "src/main/java/com/althaus/dev/GestorPeregrinos/view/login/Login.exe";
            String[] command = {path};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            readStreamAsync(process.getInputStream());

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("El proceso se ha completado correctamente.");
            } else {
                System.err.println("El proceso ha finalizado con un código de salida no válido: " + exitCode);
            }
            System.exit(exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Saliendo del programa");
            System.exit(1);
        }
    }

    private void readStreamAsync(final java.io.InputStream stream) {
        new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder usernameBuilder = new StringBuilder();
                StringBuilder passwordBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);

                    if (usernameBuilder.length() == 0) {
                        usernameBuilder.append(line);
                    } else if (passwordBuilder.length() == 0) {
                        passwordBuilder.append(line);
                    }

                    if (usernameBuilder.length() > 0 && passwordBuilder.length() > 0) {
                        String username = usernameBuilder.toString().toLowerCase();
                        String password = passwordBuilder.toString();

                        if (!username.isEmpty() && !password.isEmpty()) {
                            verificarCredenciales(username, password);
                        } else {
                            System.err.println("Los datos introducidos son incorrectos");
                        }

                        usernameBuilder.setLength(0);
                        passwordBuilder.setLength(0);
                    }
                }

                } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void verificarCredenciales(String username, String password) {

        boolean inicioSesionExitoso = credencialesService.iniciarSesion(username, password);

        if (inicioSesionExitoso) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Inicio de sesión fallido. Verifica tus credenciales.");
        }
    }
}
