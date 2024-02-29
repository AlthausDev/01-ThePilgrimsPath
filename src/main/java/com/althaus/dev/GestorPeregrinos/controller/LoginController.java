package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con el inicio de sesión y la seguridad de credenciales.
 *
 * @author Althaus_Dev
 */
@Controller
public class LoginController {

    private static final int MAX_INTENTOS_FALLIDOS = 5;
    private static final long TIEMPO_BLOQUEO = 300000;
    private int intentosFallidos = 1;
    private final CredencialesService credencialesService;
    private final ValidationService validationService;
    private Process process;

    /**
     * Constructor que inicializa las dependencias del controlador.
     *
     * @param credencialesService Servicio de Credenciales.
     * @param validationService   Servicio de Validación.
     */
    @Autowired
    public LoginController(CredencialesService credencialesService, ValidationService validationService) {
        this.credencialesService = credencialesService;
        this.validationService = validationService;
    }

    /**
     * Realiza el proceso de inicio de sesión utilizando un programa externo.
     */
    public void login() {

        try {
            String path = "src/main/java/com/althaus/dev/GestorPeregrinos/view/login/Login.exe";
            String[] command = {path};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            this.process = processBuilder.start();
            readStreamAsync(process.getInputStream());

            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Saliendo del programa");
        }
    }

    /**
     * Lee de forma asíncrona las líneas de un InputStream y procesa las credenciales.
     *
     * @param stream InputStream para leer las líneas.
     */
    //TODO Este método está pendiente de revisión. Llama a la validación de credenciales 2 veces por iteración.
    private void readStreamAsync(final InputStream stream) {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String username = null;
                String password;

                String line;
                while ((line = reader.readLine()) != null) {

                    if (username == null) {
                        username = line;
                    } else {
                        password = line;

                        if (validationService.validarDatosIngresados(username, password)) {
                            verificarCredenciales(username, password);
                        }

                        // Reiniciar username y password para la siguiente iteración
                        username = null;
                        password = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Verifica las credenciales ingresadas y realiza las acciones correspondientes.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     */
    private void verificarCredenciales(String username, String password) {
        boolean inicioSesionExitoso = credencialesService.iniciarSesion(username, password);

        if (inicioSesionExitoso) {
            System.out.println("Inicio de sesión exitoso.");
            intentosFallidos = 0;
            process.destroy();

        } else {
            System.out.println("Inicio de sesión fallido. Verifica que las credenciales introducidas son correctas.");
            System.out.println("Intentos fallidos: " + intentosFallidos++
                    + "\nLimite de intentos: " + MAX_INTENTOS_FALLIDOS);
            if (intentosFallidos > MAX_INTENTOS_FALLIDOS) {
                bloquearPrograma();
            }
        }
    }

    /**
     * Bloquea el programa si se alcanza el límite de intentos fallidos, durante un tiempo determinado.
     */
    private void bloquearPrograma() {
        System.out.println("Se alcanzó el límite de intentos fallidos. El programa quedará bloqueado durante "
                + TIEMPO_BLOQUEO / 1000 / 60 + " minutos.");

        long tiempoInicio = System.currentTimeMillis();

        try {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
                    long tiempoRestante = TIEMPO_BLOQUEO - tiempoTranscurrido;

                    if (tiempoRestante > 0) {
                        long minutos = tiempoRestante / 1000 / 60;
                        long segundos = (tiempoRestante / 1000) % 60;

                        System.out.println("Tiempo restante: " + minutos + " minutos y " + segundos + " segundos");
                    } else {
                        System.out.println("Finalización del tiempo de espera. Intente iniciar sesión nuevamente.");
                        intentosFallidos = 0;
                        timer.cancel();
                    }
                }
            }, 0, 5000);

            Thread.sleep(TIEMPO_BLOQUEO);

        } catch (InterruptedException e) {
            System.out.println("Saliendo del programa");
        }
    }
}
