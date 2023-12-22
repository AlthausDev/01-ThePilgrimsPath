package com.althaus.dev.GestorPeregrinos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginController {
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
}
