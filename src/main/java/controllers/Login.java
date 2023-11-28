package controllers;

import dao.CredencialDAOImpl;
import service.Sesion;
import java.util.Scanner;

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
    public long login() {
        String username;
        String password;
        long userId;
        CredencialDAOImpl credencialDAO = new CredencialDAOImpl();

        do {
            System.out.println("Introduzca su nombre de usuario:");
            username = sc.nextLine().toLowerCase();
            if (username.isEmpty()) {
                System.err.println("Por favor, introduzca su nombre de usuario");
            } else {
                break;
            }
        } while (true);

        do {
            System.out.println("Introduzca su contraseña:");
            password = sc.nextLine().toLowerCase();
            if (password.isEmpty()) {
                System.err.println("Por favor, introduzca su contraseña de usuario");
            } else {
                break;
            }
        } while (true);

        userId = credencialDAO.readByUserAndPassword(username, password);

        if (userId == -1) {
            System.out.println("Usuario o contraseña incorrectos.\n");
        }
        return userId;
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public static void cerrarSesion() {
        Sesion.setUserId(-1);
        System.out.println("Cierre de sesión exitoso.");
    }
}
