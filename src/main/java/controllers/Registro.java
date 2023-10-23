package controllers;

import aplicacion.Sesion;
import entities.AdminParada;
import entities.Carnet;
import entities.Parada;
import entities.Peregrino;

import java.util.ArrayList;
import java.util.Scanner;

import static entities.Perfil.ADMIN_PARADA;
import static entities.Perfil.PEREGRINO;
import static io.Escritor.*;

public class Registro {
    private static Scanner sc = new Scanner(System.in);


    public void registrar() {

    }

    public void nuevoPeregrino() {
        try {
            long id = Sesion.getLastId() + 1;

            System.out.println("Registrar nuevo usuario - Peregrino");

            System.out.print("Indique su nombre: ");
            String nombre = obtenerNombre();

            System.out.print("Indique una contraseña: ");
            String pass = obtenerPassword();

            System.out.println("CODIGO - PAIS");
            Sesion.getNacionalidades().forEach((k, v) -> System.out.println(k + " - " + v));

            String codNacionalidad = obtenerCodigoNacionalidad();
            String nacionalidad = Sesion.getNacionalidades().get(codNacionalidad);

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
            String nombreAdminParada = obtenerNombre();

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

    private static String obtenerNombre() {
        while (true) {
            System.out.print("Indique su nombre (mínimo 3 caracteres): ");
            String nombre = sc.nextLine().trim();

            if (nombre.length() >= 3) {
                return nombre;
            } else {
                System.err.println("El nombre debe tener al menos 3 caracteres. Intente de nuevo.");
            }
        }
    }

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

    private static char obtenerRegion() {
        while (true) {
            String input = sc.nextLine();
            if (input.length() == 1) {
                char region = input.charAt(0);
                if (Character.isLetter(region)) {
                    return Character.toUpperCase(region);
                }
            }
            System.err.println("Ingrese una región válida (un solo carácter). Intente de nuevo.");
        }
    }

    private static boolean paradaExiste(String nombreParada) {
        return Sesion.getParadas().values().stream()
                .anyMatch(parada -> parada.getNombre().equalsIgnoreCase(nombreParada));
    }



}

