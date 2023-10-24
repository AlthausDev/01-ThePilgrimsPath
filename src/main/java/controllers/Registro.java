package controllers;

import aplicacion.Sesion;
import entities.AdminParada;
import entities.Carnet;
import entities.Parada;
import entities.Peregrino;

import java.util.ArrayList;
import java.util.Scanner;
import static entities.Perfil.*;
import static io.Escritor.*;

/**
 * Esta clase proporciona métodos para registrar nuevos usuarios (peregrinos y administradores de parada).
 *
 * @author S.Althaus
 */
public class Registro {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Registra un nuevo peregrino en el sistema.
     */
    public void nuevoPeregrino() {
        try {
            long id = Sesion.getLastId() + 1;

            System.out.println("Registrar nuevo usuario - Peregrino");

            String nombre;
            String pass;

            do {
                // Solicitar y validar el nombre
                nombre = obtenerNombre();
                if (nombre.length() < 3) {
                    System.err.println("El nombre debe tener al menos 3 caracteres.");
                }
            } while (nombre.length() < 3);

            if (nombreExiste(nombre)) {
                System.err.println("El nombre ya existe en el sistema.");
                return;
            }

            do {
                // Solicitar y validar la contraseña
                pass = obtenerPassword();
                if (pass.length() < 3) {
                    System.err.println("La contraseña debe tener al menos 3 caracteres.");
                }
            } while (pass.length() < 3);

            System.out.println("CODIGO - PAIS");
            Sesion.getNacionalidades().forEach((k, v) -> System.out.println(k + " - " + v));

            // Solicitar y validar el código de nacionalidad
            String codNacionalidad;
            String nacionalidad;
            do {
                codNacionalidad = obtenerCodigoNacionalidad();
                nacionalidad = Sesion.getNacionalidades().get(codNacionalidad);
                if (nacionalidad == null) {
                    System.err.println("Código de nacionalidad inválido.");
                }
            } while (nacionalidad == null);

            ArrayList<Parada> paradaActual = new ArrayList<>();
            paradaActual.add(Sesion.getParadaActual());

            Carnet carnet = new Carnet(id, Sesion.getParadaActual());
            Peregrino nuevoPeregrino = new Peregrino(id, nombre, nacionalidad, carnet, paradaActual);

            writeCarnet(nuevoPeregrino);
            writeCredencial(nombre, pass, PEREGRINO, id);

            Sesion.setLastId(id);
        } catch (Exception e) {
            System.err.println("Error al registrar el nuevo peregrino: " + e.getMessage());
        }
    }

    /**
     * Registra una nueva parada en el sistema.
     */
    public static void nuevaParada() {
        try {
            System.out.println("Agregar nueva parada");
            long id = Sesion.getLastId() + 1;

            System.out.print("Nombre de la nueva parada: ");
            String nombreParada = obtenerNombre();

            if (paradaExiste(nombreParada)) {
                System.err.println("La parada ya existe en el sistema.");
                return;
            }

            System.out.print("Región de la nueva parada: ");
            char regionParada = obtenerRegion();

            System.out.print("Nombre del Administrador de Parada: ");
            String nombreAdminParada = obtenerNombre().toLowerCase();

            System.out.print("Contraseña del Administrador de Parada: ");
            String passAdminParada = obtenerPassword();

            AdminParada adminParada = new AdminParada(id, nombreAdminParada);
            Parada nuevaParada = new Parada(id, nombreParada, regionParada, adminParada);

            writeCredencial(nombreAdminParada, passAdminParada, ADMIN_PARADA, id);
            writeParada(nuevaParada);

            System.out.println("Nueva parada agregada con éxito.");
            Sesion.setLastId(id);
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada: " + e.getMessage());
        }
    }

    /**
     * Obtiene un nombre válido del usuario.
     *
     * @return Nombre válido ingresado por el usuario.
     */
    private static String obtenerNombre() {
        while (true) {
            System.out.print("Indique su nombre (mínimo 3 caracteres): ");
            String nombre = sc.nextLine().trim().toLowerCase();

            if (nombre.length() >= 3) {
                return nombre;
            } else {
                System.err.println("El nombre debe tener al menos 3 caracteres. Intente de nuevo.");
            }
        }
    }

    /**
     * Obtiene una contraseña válida del usuario.
     *
     * @return Contraseña válida ingresada por el usuario.
     */

    private static String obtenerPassword() {
        while (true) {
            System.out.print("Indique una contraseña (mínimo 3 caracteres): ");
            String pass = sc.nextLine().trim();

            if (pass.length() >= 3) {
                return pass;
            } else {
                System.err.println("La contraseña debe tener al menos 3 caracteres. Intente de nuevo.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese el código de su país y verifica que tenga 2 caracteres y sea válido.
     *
     * @return El código de nacionalidad ingresado por el usuario.
     */
    private String obtenerCodigoNacionalidad() {
        while (true) {
            System.out.print("Inserte el código de su país (2 caracteres): ");
            String codNacionalidad = sc.nextLine().toUpperCase();

            if (codNacionalidad.length() == 2 && Sesion.getNacionalidades().containsKey(codNacionalidad)) {
                return codNacionalidad;
            } else {
                System.err.println("Código de país no válido. Debe contener 2 caracteres y ser válido. Intente de nuevo.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese una región y verifica que tenga 2 caracteres y sea una letra.
     *
     * @return La región ingresada por el usuario en mayúscula.
     */
    private static char obtenerRegion() {
        while (true) {
            String input = sc.nextLine();
            if (input.length() == 2) {
                char region = input.charAt(0);
                if (Character.isLetter(region)) {
                    return Character.toUpperCase(region);
                }
            }
            System.err.println("Ingrese una región válida (2 caracteres). Intente de nuevo.");
        }
    }

    /**
     * Verifica si un nombre de usuario existe en la colección de usuarios válidos.
     *
     * @param nombre El nombre de usuario a verificar.
     * @return true si el nombre de usuario existe, de lo contrario false.
     */
    private static boolean nombreExiste(String nombre) {
        return Sesion.validUsers.containsKey(nombre);
    }

    /**
     * Verifica si un nombre de parada existe en la colección de paradas.
     *
     * @param nombreParada El nombre de la parada a verificar.
     * @return true si el nombre de parada existe, de lo contrario false.
     */
    private static boolean paradaExiste(String nombreParada) {
        return Sesion.getParadas().values().stream()
                .anyMatch(parada -> parada.getNombre().equalsIgnoreCase(nombreParada));
    }
}
