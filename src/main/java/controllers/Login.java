package controllers;

import service.Sesion;
import model.Parada;
import model.Perfil;
import util.Validation;
import java.util.Scanner;
import static model.Perfil.*;
import static model.io.Lector.readCarnet;

/**
 * La clase `Login` se encarga de gestionar el inicio de sesión de usuarios.
 *
 * @author S.Althaus
 */
public class Login {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Permite a los usuarios iniciar sesión proporcionando su nombre de usuario y contraseña.
     */
    public void login() {
        String username;
        String password;

        // Solicitar el nombre de usuario y validar que no esté vacío
        do {
            System.out.println("Introduzca su nombre de usuario:");
            username = sc.nextLine().toLowerCase();
            if (username.isEmpty()) {
                System.err.println("Por favor, introduzca su nombre de usuario");
            } else {
                break;
            }
        } while (true);

        // Solicitar la contraseña y validar que no esté vacía
        do {
            System.out.println("Introduzca su contraseña:");
            password = sc.nextLine().toLowerCase();
            if (password.isEmpty()) {
                System.err.println("Por favor, introduzca su contraseña de usuario");
            } else {
                break;
            }
        } while (true);

        // Validar las credenciales del usuario
        if (Validation.userValidator(username, password) != null) {
            Perfil perfil = Validation.userValidator(username, password);
            iniciarSesion(username, perfil);
        } else {
            System.out.println("Usuario o contraseña incorrectos.\n");
        }
    }

    /**
     * Inicia la sesión del usuario con el nombre de usuario proporcionado y su perfil.
     * Si es un peregrino, se asocia con un carnet; si es un administrador de parada, se asocia con la parada correspondiente.
     *
     * @param username Nombre de usuario.
     * @param perfil   Perfil del usuario.
     */
    public static void iniciarSesion(String username, Perfil perfil) {
        Sesion.setPerfil(perfil);
        System.out.println("Iniciando sesión... Bienvenido " + username + "\n");

        if (perfil == Perfil.PEREGRINO) {
            Sesion.setUser(readCarnet(username));
        } else if (perfil == Perfil.ADMIN_PARADA) {
            for (Parada parada : Sesion.getParadas().values()) {
                if (parada.getAdminParada() != null && parada.getAdminParada().getName().equals(username)) {
                    Sesion.setParadaActual(parada);
                    break;
                }
            }
        }
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public static void cerrarSesion() {
        Sesion.setUser(null);
        Sesion.setPerfil(INVITADO);
        System.out.println("Cierre de sesión exitoso.");
    }
}
