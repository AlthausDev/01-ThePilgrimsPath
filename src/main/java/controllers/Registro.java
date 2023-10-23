package controllers;

import aplicacion.Sesion;
import entities.Carnet;
import entities.Parada;
import entities.Peregrino;

import java.util.ArrayList;
import java.util.Scanner;

import static entities.Perfil.PEREGRINO;
import static io.Escritor.writeCarnet;
import static io.Escritor.writeCredencial;

public class Registro {
    private Scanner sc = new Scanner(System.in);


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

    private String obtenerNombre() {
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

    private String obtenerPassword() {
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



    private void nuevaParada() {

    }

}

