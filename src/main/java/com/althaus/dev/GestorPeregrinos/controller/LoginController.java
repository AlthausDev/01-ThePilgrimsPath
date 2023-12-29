package com.althaus.dev.GestorPeregrinos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginController {

    private static final LoginController INSTANCE = new LoginController();
    public LoginController() {
        try {
            String path = "src/main/java/com/althaus/dev/GestorPeregrinos/view/login/Login.exe";
            String[] command = {path};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            readStreamAsync(process.getInputStream());
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        int exitCode = process.waitFor();
//
//        if (exitCode == 0) {
//            System.out.println("El proceso se ha completado correctamente.");
//        } else {
//            System.err.println("El proceso ha finalizado con un código de salida no válido: " + exitCode);
//        }
//        System.exit(exitCode);
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//        System.out.println("Saliendo del programa");
//        System.exit(1);
//    }
    }

    private void readStreamAsync(final java.io.InputStream stream) {
        new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static LoginController getInstance() {
        return INSTANCE;
    }
}
