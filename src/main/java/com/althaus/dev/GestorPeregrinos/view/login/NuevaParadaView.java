package com.althaus.dev.GestorPeregrinos.view.login;

import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Scanner;

@Component
public class NuevaParadaView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ValidationService validationService = new ValidationService();

    public HashMap<String, Object> agregarParada() {
        HashMap<String, Object> paradaData = new HashMap<>();

        try {
            System.out.println("Agregar nueva parada");
            paradaData.put("nombre", obtenerNombre());

            System.out.println("Indique la región a la que pertenece de la nueva parada:");
            paradaData.put("region", obtenerRegion());

            System.out.println("Nuevo administrador de Parada");
            paradaData.put("nombreAdmin", obtenerNombre());
            paradaData.put("passAdmin", obtenerPassword());

            System.out.println("Datos Introducidos:"
                    + "\nNombre de la nueva parada: " + paradaData.get("nombre")
                    + "\nRegión de la nueva parada: " + paradaData.get("region")
                    + "\nNombre del administrador de parada: " + paradaData.get("nombreAdmin"));

            if (!isCorrecto()) {
                System.out.println("Saliendo del formulario de registro.");
                return null;
            }
            return paradaData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String obtenerNombre() {
        boolean cont = false;
        String nombre = null;

        while (cont) {
            System.out.println("Indique un nombre (mínimo 3 caracteres): ");
            nombre = scanner.nextLine().toLowerCase();
            cont = validationService.validarFormatoNombreUsuario(nombre);
        }
        return nombre;
    }

    private String obtenerPassword() {
        while (true) {
            System.out.println("Indique una contraseña (mínimo 3 caracteres): ");
            String pass = scanner.nextLine();

            if (pass.length() >= 3) {
                return pass;
            } else {
                System.err.println("La contraseña debe tener al menos 3 caracteres. Vuelva a introducirla.");
            }
        }
    }

    private char obtenerRegion() {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                char region = input.charAt(0);
                if (Character.isLetter(region)) {
                    return Character.toUpperCase(region);
                }
            }
            System.err.println("Debe introducir una región válida (1 caracter). Vuelva a introducirlo.");
        }
    }

    private boolean isCorrecto() {
        char valido;
        do {
            System.out.println("¿Son los datos introducidos son correctos? S/N");
            valido = scanner.nextLine().toUpperCase().charAt(0);
        } while (valido != 'S' && valido != 'N');
        return valido == 'S';
    }
}
