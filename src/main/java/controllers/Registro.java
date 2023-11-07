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
import static io.Lector.readParadas;

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

        ArrayList <Parada> paradaActual = null;

        try {
            long id = Sesion.getLastId() + 1;

            System.out.println("Registrar nuevo usuario - Peregrino");

            String nombre;
            String pass;

            System.out.println(readParadas().values().toString());

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

            System.out.println("Datos Introducidos:"
                    + "\nNombre del nuevo peregrino: " + nombre
                    + "\nNacionalidad: " + nacionalidad
                    + "\nParada: " );

            if(!isCorrecto()) return;

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
            String nombreParada = obtenerNombre();

            System.out.println("Indique la región a la que pertenece de la nueva parada:");
            char regionParada = obtenerRegion();

            if (paradaExiste(nombreParada, regionParada)) {
                System.out.println("La parada ya existe en el sistema.\n" +
                        "Volviendo al menu\n");
                return;
            }

            String nombreAdminParada;
            String passAdminParada;

            do {
                System.out.println("Nuevo administrador de Parada");
                nombreAdminParada = obtenerNombre().toLowerCase();
                passAdminParada = obtenerPassword();
            } while (adminParadaExiste(nombreAdminParada));


            System.out.println("Datos Introducidos:"
                    + "\nNombre de la nueva parada: " + nombreParada
                    + "\nRegión de la nueva parada: " + regionParada
                    + "\nNombre del administrador de parada: " + nombreAdminParada);

            if (!isCorrecto()){
                System.out.println("Saliendo del formulario de registro.");
                return;
            }

            AdminParada adminParada = new AdminParada(id, nombreAdminParada);
            Parada nuevaParada = new Parada(id, nombreParada, regionParada, adminParada);

            writeCredencial(nombreAdminParada, passAdminParada, ADMIN_PARADA, id);
            writeParada(nuevaParada);

            System.out.println("Nueva parada agregada con éxito.");
            Sesion.setLastId(id);
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada");
        }
    }


    /**
     * Obtiene un nombre válido del usuario.
     *
     * @return Nombre válido ingresado por el usuario.
     */
    private static String obtenerNombre() {
        while (true) {
            System.out.println("Indique un nombre (mínimo 3 caracteres): ");
            String nombre = sc.nextLine().toLowerCase();

            if (nombre.length() >= 3 && nombre.matches("[A-Za-z]+")) {
                return nombre;
            } else {
                System.err.println("El nombre debe tener al menos 3 caracteres y solo puede contener letras. Vuelva a introducirlo.");
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
            System.out.println("Indique una contraseña (mínimo 3 caracteres): ");
            String pass = sc.nextLine();

            if (pass.length() >= 3) {
                return pass;
            } else {
                System.err.println("La contraseña debe tener al menos 3 caracteres. Vuelva a introducirla.");
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
            System.out.println("Inserte el código de su país (2 caracteres): ");
            String codNacionalidad = sc.nextLine().toUpperCase();

            if (codNacionalidad.length() == 2 && Sesion.getNacionalidades().containsKey(codNacionalidad)) {
                return codNacionalidad;
            } else {
                System.err.println("Código de país no válido. Debe contener 2 caracteres y ser válido. Vuelva a introducirlo.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese una región y verifica que tenga 2 caracteres y letra.
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
            System.err.println("Ingrese una región válida (2 caracteres). Vuelva a introducirlo.");
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
     * Verifica si un nombre de parada y región coinciden con alguna de las paradas en la colección.
     *
     * @param nombreParada El nombre de la parada a verificar.
     * @param regionParada La región de la parada a verificar.
     * @return true si se encuentra una parada con el nombre y la región especificados, de lo contrario, false.
     */

    private static boolean paradaExiste(String nombreParada, char regionParada) {

        try {
            for (Parada parada : Sesion.getParadas().values()) {
                if (parada.getNombre().equalsIgnoreCase(nombreParada) && parada.getRegion() == regionParada) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    private static boolean adminParadaExiste(String nombreAdminParada) {
        if(Sesion.validUsers.containsKey(nombreAdminParada)){
            System.out.println("El usuario con nombre: " + nombreAdminParada + " ya existe en el sistema.\n" +
                    "Elija un nuevo nombre de usuario \n");
            return true;
        }
        return false;
    }


    private static boolean isCorrecto() {

        char valido;
        do {
            System.out.println("¿Son los datos introducidos son correctos? S/N");
            valido = sc.nextLine().toUpperCase().charAt(0);
        } while (valido != 'S' && valido != 'N');
        return valido == 'S';
    }
}